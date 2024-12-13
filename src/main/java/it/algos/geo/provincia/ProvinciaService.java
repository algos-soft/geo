package it.algos.geo.provincia;

import it.algos.geo.logic.GeoModuloService;
import it.algos.geo.regione.RegioneEntity;
import it.algos.geo.regione.RegioneService;
import it.algos.vbase.enumeration.RisultatoReset;
import it.algos.vbase.enumeration.TypeLog;
import it.algos.vbase.wrapper.WrapLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.algos.vbase.boot.BaseCost.*;

/**
 * Project base24
 * Created by Algos
 * User: gac
 * Date: Sat, 03-Feb-2024
 * Time: 09:48
 */
@Slf4j
@Service
public class ProvinciaService extends GeoModuloService<ProvinciaEntity> {

    @Value("${algos.project.usa.dir.geo:true}")
    private boolean usaDirGeo;

    @Autowired
    RegioneService regioneModulo;

    private Map<String, ProvinciaEntity> mappaBeans= new HashMap<>();

    private ProvinciaEntity entityBean;

    /**
     * Costruttore invocato dalla sottoclasse concreta obbligatoriamente con due parametri <br>
     * Regola la entityClazz associata a questo Modulo <br>
     * Regola la viewClazz @Route associata a questo Modulo <br>
     */
    public ProvinciaService() {
        super(ProvinciaEntity.class);
    }

    /**
     * Creazione in memoria di una nuova entity che NON viene salvata <br>
     *
     * @param nome         (obbligatorio)
     * @param nomeCompleto (facoltativa)
     * @param regione      (facoltativa)
     *
     * @return la nuova entity appena creata (con keyID ma non salvata)
     */
    public ProvinciaEntity newEntity(int ordine, String sigla, String nome, String nomeCompleto, String cap, RegioneEntity regione) {
        ProvinciaEntity newEntityBean = ProvinciaEntity.builder()
                .sigla(textService.isValid(sigla) ? sigla : null)
                .nome(textService.isValid(nome) ? nome : null)
                .nomeCompleto(textService.isValid(nomeCompleto) ? nomeCompleto : null)
                .cap(cap)
                .regione(regione)
                .build();

        newEntityBean.setOrdine(ordine == 0 ? nextOrdine() : ordine);
        return newEntityBean;
    }


    @Override
    public ObjectId getObjectId(ProvinciaEntity newEntityBean) {
        return new ObjectId(textService.fixSize(((ProvinciaEntity) newEntityBean).getSigla(), ID_LENGTH).getBytes());
    }

    @Override
    public List<ProvinciaEntity> findAll() {
        return super.findAll();
    }

    @Override
    public ProvinciaEntity findById(final String idStringValue) {
        return (ProvinciaEntity) super.findById(idStringValue);
    }


    public ProvinciaEntity findByNome(String nome) {
        return this.findOneByProperty("nome", nome);
    }

    public ProvinciaEntity findByNomeCompleto(String nomeCompleto) {
        return this.findOneByProperty("nomeCompleto", nomeCompleto);
    }

    public ProvinciaEntity findOneByProperty(String keyPropertyName, Object keyPropertyValue) {
        return (ProvinciaEntity) super.findOneByProperty(keyPropertyName, keyPropertyValue);
    }


    public RisultatoReset reset() {
        String collectionName = mongoTemplate.getCollectionName(ProvinciaEntity.class);
        if (!usaDirGeo) {
            return RisultatoReset.nonCostruito;
        }
        if (regioneModulo.count() < 1) {
            regioneModulo.addItaliaOnly();
        }

        this.leggeProvince();
        this.leggeCap();

        if (mappaBeans.size() > 0) {
            return super.bulkInsertEntitiesDelete(mappaBeans.values().stream().toList());
        } else {
            log.warn(String.format("Collection [%s] non costruita.", collectionName));
            return RisultatoReset.nonCostruito;
        }
    }


    private void leggeProvince() {
        String nomeFileCSV = "province";
        String message;
        String code;
        String nome;
        String nomeCompleto;
        String regioneTxt;
        RegioneEntity regioneBean;
        int cont = 0;

        Map<String, List<String>> mappaSource = resourceService.leggeMappa(nomeFileCSV);
        if (mappaSource != null && mappaSource.size() > 0) {
            for (List<String> rigaUnValore : mappaSource.values()) {
                code = rigaUnValore.get(0);
                nome = rigaUnValore.get(1);
                nomeCompleto = rigaUnValore.size() > 2 ? rigaUnValore.get(2) : VUOTA;
                regioneTxt = rigaUnValore.size() > 3 ? rigaUnValore.get(3) : VUOTA;
                regioneBean = textService.isValid(regioneTxt) ? regioneModulo.findById(regioneTxt) : null;
                entityBean = newEntity(++cont, code, nome, nomeCompleto, VUOTA, regioneBean);
                mappaBeans.put(code, entityBean);
            }
        }
        else {
            message = String.format("Manca il file [%s] nella directory /config o sul server", nomeFileCSV);
            log.warn(message);
        }
    }


    private void leggeCap() {
        String nomePaginaWiki = "Codice di avviamento postale";
        String testoTable;
        String tagTable = "\\|-";
        String[] righeTable;
        String[] partiRiga;
        String tag1 = "align=center";
        String tag2 = "align=left";
        String sigla;
        String cap;
        String sep = CAPO_SPLIT + PIPE;

        testoTable = webService.leggeWikiTable(nomePaginaWiki, 2);
        testoTable = webService.estraeTable(testoTable);
        righeTable = testoTable.split(tagTable);

        if (righeTable != null) {
            for (String riga : righeTable) {
                partiRiga = StringUtils.splitByWholeSeparator(riga, sep);
                sigla = partiRiga[1].trim();
                sigla = textService.levaTesta(sigla, tag1).trim();
                sigla = textService.levaTesta(sigla, PIPE).trim();

                cap = partiRiga[4].trim();
                cap = textService.levaTesta(cap, tag2).trim();
                cap = textService.levaTesta(cap, PIPE).trim();
                cap = textService.levaCodaDaPrimo(cap, PARENTESI_TONDA_INI).trim();
                if (textService.isValid(sigla) && textService.isValid(cap)) {
                    if (mappaBeans.containsKey(sigla)) {
                        entityBean =  mappaBeans.get(sigla);
                        entityBean.setCap(cap);
                        mappaBeans.put(sigla, entityBean);
                    }
                }
            }
        }
    }

}// end of CrudService class
