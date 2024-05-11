package it.algos.geo.comune;

import it.algos.geo.logic.*;
import it.algos.geo.provincia.*;
import it.algos.geo.regione.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.enumeration.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.inject.*;
import java.util.*;

/**
 * Project base24
 * Created by Algos
 * User: gac
 * Date: Sat, 03-Feb-2024
 * Time: 09:13
 */
@Service
public class ComuneService extends GeoModuloService {

    @Value("${algos.project.crea.directory.geo:false}")
    private String creaDirectoryGeoTxt;

    @Inject
    ProvinciaService provinciaModulo;

    @Inject
    RegioneService regioneModulo;

    private ComuneEntity entityBean;

    /**
     * Costruttore invocato dalla sottoclasse concreta obbligatoriamente con due parametri <br>
     * Regola la entityClazz associata a questo Modulo <br>
     * Regola la viewClazz @Route associata a questo Modulo <br>
     */
    public ComuneService() {
        super(ComuneEntity.class, ComuneView.class);
    }


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata <br>
     *
     * @param code      (obbligatorio)
     * @param provincia (facoltativo)
     * @param cap       (facoltativo)
     * @param regione   (facoltativo)
     *
     * @return la nuova entity appena creata (con keyID ma non salvata)
     */
    public ComuneEntity newEntity(int ordine, String code, ProvinciaEntity provincia, String cap, RegioneEntity regione) {
        ComuneEntity newEntityBean = ComuneEntity.builder()
                .code(textService.isValid(code) ? code : null)
                .provincia(provincia)
                .cap(textService.isValid(cap) ? cap : null)
                .regione(regione)
                .build();

        newEntityBean.setOrdine(ordine == 0 ? nextOrdine() : ordine);
        return newEntityBean;
    }

    @Override
    public List<ComuneEntity> findAll() {
        return super.findAll();
    }


    @Override
    public ComuneEntity findByCode(final String keyCodeValue) {
        return (ComuneEntity) super.findByCode(keyCodeValue);
    }


    public RisultatoReset reset() {
        if (!Boolean.parseBoolean(creaDirectoryGeoTxt)) {
            return RisultatoReset.nonCostruito;
        }
        if (provinciaModulo.count() < 1) {
            provinciaModulo.resetDelete();
        }

        String tag = "Comuni d'Italia";
        List<String> lettere = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H-J", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "Z");
        String wikiTitle;
        int cont = 0;

        for (String key : lettere) {
            wikiTitle = String.format("%s%s%s%s%s", tag, SPAZIO, PARENTESI_TONDA_INI, key, PARENTESI_TONDA_END);
            cont = addComuniPerLettera(cont, wikiTitle);
        }

        mappaBeans.values().stream().forEach(bean -> insertSave(bean));
        return RisultatoReset.vuotoMaCostruito;
    }


    private int addComuniPerLettera(int cont, String wikiTitle) {
        List<List<String>> mappa = webService.getWikiTable(wikiTitle);
        String code;
        String provinciaTxt;
        ProvinciaEntity provinciaBean = null;
        String regioneTxt;
        RegioneEntity regioneBean = null;
        String cap;

        for (List<String> rigaUnValore : mappa) {
            code = rigaUnValore.get(0);

            provinciaTxt = rigaUnValore.size() > 1 ? rigaUnValore.get(1) : VUOTA;
            if (textService.isValid(provinciaTxt)) {
                provinciaBean = provinciaModulo.findByNome(provinciaTxt);
            }

            regioneTxt = rigaUnValore.size() > 2 ? rigaUnValore.get(2) : VUOTA;
            if (textService.isValid(regioneTxt)) {
                regioneBean = regioneModulo.findByCode(regioneTxt);
            }

            entityBean = newEntity(++cont, code, provinciaBean, VUOTA, regioneBean);
            mappaBeans.put(code, entityBean);
        }

        return cont;
    }


    public void elabora() {
        String nomePaginaWiki;
        String sorgentePaginaWiki;
        String sorgentePaginaWikiData;
        String tagWikiData = "wgWikibaseItemId";
        String wikiDataPage;
        String tagUno = "P281";
        String tagDue = "wikibase-snakview-variation-valuesnak";
        int posIni;
        int posEnd;
        String tagWikiDataPage="https://www.wikidata.org/wiki/";
        String cap;

        for (ComuneEntity comune : findAll().subList(0, 100)) {
            if (textService.isValid(comune.getCap())) {
                continue;
            }

            nomePaginaWiki = comune.getCode();
            sorgentePaginaWiki = leggeWiki(nomePaginaWiki);
            posIni = sorgentePaginaWiki.indexOf(tagWikiData) + tagWikiData.length();
            posEnd = sorgentePaginaWiki.indexOf(VIRGOLA, posIni);
            wikiDataPage = sorgentePaginaWiki.substring(posIni, posEnd);
            wikiDataPage = textService.setNoDoppiApici(wikiDataPage);
            wikiDataPage = textService.levaTesta(wikiDataPage, DUE_PUNTI);
            wikiDataPage = textService.levaTesta(wikiDataPage, APICETTI);

            sorgentePaginaWikiData=webService.legge(tagWikiDataPage+wikiDataPage);
            posIni = sorgentePaginaWikiData.indexOf(tagUno);
            posIni = sorgentePaginaWikiData.indexOf(tagDue,posIni)+tagDue.length();
            cap = sorgentePaginaWikiData.substring(posIni);
            cap = textService.levaTesta(cap, APICETTI);
            cap = textService.levaTesta(cap, ">");
            cap = textService.levaCodaDaPrimo(cap, "<");

            if (textService.isValid(cap)) {
                comune.setCap(cap);
                save(comune);
            }
        }
    }

    public String leggeWiki(final String wikiTitleGrezzo) {
        String urlDomain = TAG_WIKI + wikiTitleGrezzo;
        return webService.legge(urlDomain);
    }

    /**
     * Only custom <br>
     */
    @Override
    public ComuneEntity getEntityRandom() {
        return (ComuneEntity) super.getEntityRandom();
    }

}// end of CrudService class
