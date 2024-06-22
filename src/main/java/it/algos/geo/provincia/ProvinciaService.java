package it.algos.geo.provincia;

import it.algos.geo.comune.*;
import it.algos.geo.continente.*;
import it.algos.geo.logic.*;
import it.algos.geo.regione.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.backend.wrapper.*;
import org.apache.commons.lang3.*;
import org.bson.types.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.inject.*;
import java.util.*;

/**
 * Project base24
 * Created by Algos
 * User: gac
 * Date: Sat, 03-Feb-2024
 * Time: 09:48
 */
@Service
public class ProvinciaService extends GeoModuloService<ProvinciaEntity> {

    @Value("${algos.project.usa.dir.geo:true}")
    private boolean usaDirGeo;

    @Inject
    RegioneService regioneModulo;

    private ProvinciaEntity entityBean;

    /**
     * Costruttore invocato dalla sottoclasse concreta obbligatoriamente con due parametri <br>
     * Regola la entityClazz associata a questo Modulo <br>
     * Regola la viewClazz @Route associata a questo Modulo <br>
     */
    public ProvinciaService() {
        super(ProvinciaEntity.class, ProvinciaView.class);
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
        if (!usaDirGeo) {
            return RisultatoReset.nonCostruito;
        }
        if (regioneModulo.count() < 1) {
            regioneModulo.addItaliaOnly();
        }

        this.leggeProvince();
        this.leggeCap();

        mappaBeans.values().stream().forEach(bean -> insertSave(bean));
        return RisultatoReset.vuotoMaCostruito;
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
            logger.warn(new WrapLog().message(message).type(TypeLog.startup));
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
                        entityBean = (ProvinciaEntity) mappaBeans.get(sigla);
                        entityBean.setCap(cap);
                        mappaBeans.put(sigla, entityBean);
                    }
                }
            }
        }
    }

}// end of CrudService class
