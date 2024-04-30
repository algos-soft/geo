package it.algos.geo.provincia;

import it.algos.geo.regione.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.backend.logic.*;
import it.algos.vbase.backend.modules.anagrafica.via.*;
import it.algos.vbase.backend.wrapper.*;
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
public class ProvinciaService extends CrudService {

    @Inject
    RegioneService regioneModulo;

    /**
     * Costruttore invocato dalla sottoclasse concreta obbligatoriamente con due parametri <br>
     * Regola la entityClazz associata a questo Modulo <br>
     * Regola la viewClazz @Route associata a questo Modulo <br>
     */
    public ProvinciaService() {
        super(ProvinciaEntity.class, ProvinciaView.class);
    }


    public ProvinciaEntity creaIfNotExists(String sigla, String nomeBreve, String nomeCompleto, RegioneEntity regione) {
        if (existByKey(sigla)) {
            return null;
        }
        else {
            return (ProvinciaEntity) insert(newEntity(sigla, nomeBreve, nomeCompleto, regione));
        }
    }


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata <br>
     *
     * @return la nuova entity appena creata (con keyID ma non salvata)
     */
    @Override
    public ProvinciaEntity newEntity() {
        return newEntity(VUOTA, VUOTA, VUOTA, null);
    }

    public ProvinciaEntity newEntity(final String sigla, final String nomeBreve) {
        return newEntity(sigla, nomeBreve, VUOTA, null);
    }

    /**
     * Creazione in memoria di una nuova entity che NON viene salvata <br>
     *
     * @param sigla        (obbligatorio)
     * @param nomeBreve    (obbligatorio)
     * @param nomeCompleto (facoltativa)
     * @param regione      (facoltativa)
     *
     * @return la nuova entity appena creata (con keyID ma non salvata)
     */
    public ProvinciaEntity newEntity(final String sigla, final String nomeBreve, String nomeCompleto, RegioneEntity regione) {
        ProvinciaEntity newEntityBean = ProvinciaEntity.builder()
                .sigla(textService.isValid(sigla) ? sigla : null)
                .nomeBreve(textService.isValid(nomeBreve) ? nomeBreve : null)
                .nomeCompleto(textService.isValid(nomeCompleto) ? nomeCompleto : null)
                .regione(regione)
                .build();

        return (ProvinciaEntity) fixKey(newEntityBean);
    }

    @Override
    public List<ProvinciaEntity> findAll() {
        return super.findAll();
    }

    @Override
    public ProvinciaEntity findByKey(final Object keyPropertyValue) {
        return (ProvinciaEntity) super.findByKey(keyPropertyValue);
    }

    public ProvinciaEntity findByNomeBreve(String nomeBreve) {
        return this.findOneByProperty("nomeBreve", nomeBreve);
    }

    public ProvinciaEntity findOneByProperty(String keyPropertyName, Object keyPropertyValue) {
        return (ProvinciaEntity) super.findOneByProperty(keyPropertyName, keyPropertyValue);
    }


    @Override
    public void download() {
        reset();
    }

//    @Override
//    public RisultatoReset resetDelete() {
//        RisultatoReset typeReset = super.resetDelete();
//        return resetBase(typeReset);
//    }


    public RisultatoReset reset() {
        String nomeFileCSV = "province";
        String message;
        String sigla;
        String nomeBreve;
        String nomeCompleto;
        String regioneTxt;
        RegioneEntity regioneBean = null;
        ViaEntity newBean;

        if (regioneModulo.count() < 1) {
            regioneModulo.addItaliaOnly();
        }

        Map<String, List<String>> mappaSource = resourceService.leggeMappa(nomeFileCSV);
        if (mappaSource != null && mappaSource.size() > 0) {
            for (List<String> rigaUnValore : mappaSource.values()) {
                sigla = rigaUnValore.get(0);
                nomeBreve = rigaUnValore.get(1);
                nomeCompleto = rigaUnValore.size() > 2 ? rigaUnValore.get(2) : VUOTA;
                regioneTxt = rigaUnValore.size() > 3 ? rigaUnValore.get(3) : VUOTA;
                regioneBean = textService.isValid(regioneTxt) ? regioneModulo.findByNome(regioneTxt) : null;
                creaIfNotExists(sigla, nomeBreve, nomeCompleto, regioneBean);
            }
        }
        else {
            message = String.format("Manca il file [%s] nella directory /config o sul server", nomeFileCSV);
            logger.warn(new WrapLog().message(message).type(TypeLog.startup));
            return RisultatoReset.nonCostruito;
        }

        return RisultatoReset.vuotoMaCostruito;
    }


    //    @Deprecated
    //   private RisultatoReset resetBase() {
    //        String nomePaginaWiki = "Province d'Italia";
    //        List<List<String>> mappa = webService.getWikiTable(nomePaginaWiki);
    //        String sigla;
    //        String nomeBreve;
    //        String nomeCompleto;
    //
    //        for (List<String> riga : mappa) {
    //            sigla = riga.get(2);
    //            nomeBreve = webService.getNomeDaLink(sigla);
    //            insert(newEntity(sigla, nomeBreve));
    //        }
    //
    //        return null;
    //    }

}// end of CrudService class
