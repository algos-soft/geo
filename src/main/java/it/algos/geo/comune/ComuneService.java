package it.algos.geo.comune;

import it.algos.geo.provincia.*;
import it.algos.geo.regione.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.backend.logic.*;
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
public class ComuneService extends ModuloService {

    @Inject
    ProvinciaService provinciaModulo;

    @Inject
    RegioneService regioneModulo;

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
     * @param nome      (obbligatorio)
     * @param provincia (facoltativo)
     * @param cap       (facoltativo)
     * @param regione   (facoltativo)
     *
     * @return la nuova entity appena creata (con keyID ma non salvata)
     */
    public ComuneEntity newEntity(String code, ProvinciaEntity provincia, String cap, RegioneEntity regione) {
        ComuneEntity newEntityBean = ComuneEntity.builder()
                .code(textService.isValid(code) ? code : null)
                .provincia(provincia)
                .cap(textService.isValid(cap) ? cap : null)
                .regione(regione)
                .build();

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


    @Override
    public void download() {
        reset();
    }

    //    @Override
    //    public RisultatoReset resetDelete() {
    //        RisultatoReset typeReset = super.resetDelete();
    //        return resetBase();
    //    }

    public RisultatoReset reset() {
        String tag = "Comuni d'Italia";
        List<String> lettere = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H-J", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "Z");
        String wikiTitle;

        if (provinciaModulo.count() < 1) {
            provinciaModulo.resetDelete();
        }

        for (String key : lettere) {
            wikiTitle = String.format("%s%s%s%s%s", tag, SPAZIO, PARENTESI_TONDA_INI, key, PARENTESI_TONDA_END);
            addComuniPerLettera(wikiTitle);
        }

        return RisultatoReset.vuotoMaCostruito;
    }


    private void addComuniPerLettera(String wikiTitle) {
        List<List<String>> mappa = webService.getWikiTable(wikiTitle);
        String nome;
        String provinciaTxt;
        ProvinciaEntity provinciaBean;
        String regioneTxt;
        RegioneEntity regioneBean;
        String cap;

        for (List<String> rigaUnValore : mappa) {
            nome = rigaUnValore.get(0);

            provinciaTxt = rigaUnValore.size() > 1 ? rigaUnValore.get(1) : VUOTA;
            provinciaBean = provinciaModulo.findByNomeBreve(provinciaTxt);

            regioneTxt = rigaUnValore.size() > 2 ? rigaUnValore.get(2) : VUOTA;
            regioneBean = regioneModulo.findByNome(regioneTxt);

//            creaIfNotExists(nome, provinciaBean, regioneBean);
        }
    }

    /**
     * Only custom <br>
     */
    @Override
    public ComuneEntity getEntityRandom() {
        return (ComuneEntity) super.getEntityRandom();
    }

}// end of CrudService class
