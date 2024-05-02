package it.algos.geo.continente;

import it.algos.geo.enumeration.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.backend.exception.*;
import it.algos.vbase.backend.logic.*;
import it.algos.vbase.backend.wrapper.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * Project base24
 * Created by Algos
 * User: gac
 * Date: dom, 29-ott-2023
 * Time: 06:59
 */
@Service
public class ContinenteService extends CrudService {

    /**
     * Regola la entityClazz associata a questo Modulo e la passa alla superclasse <br>
     * Regola la viewClazz @Route associata a questo Modulo e la passa alla superclasse <br>
     * Regola la listClazz associata a questo Modulo e la passa alla superclasse <br>
     */
    public ContinenteService() {
        super(ContinenteEntity.class, ContinenteView.class);
    }


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata <br>
     *
     * @return la nuova entity appena creata (con keyID ma non salvata)
     */
    @Override
    public ContinenteEntity newEntity() {
        return newEntity(0, VUOTA);
    }

    /**
     * Creazione in memoria di una nuova entity che NON viene salvata <br>
     *
     * @param ordine (opzionale, unico)
     * @param nome   (obbligatorio, unico)
     *
     * @return la nuova entity appena creata (con keyID ma non salvata)
     */
    public ContinenteEntity newEntity(final int ordine, final String nome) {
        ContinenteEntity newEntityBean = new ContinenteEntity();

        newEntityBean.setOrdine(ordine == 0 ? nextOrdine() : ordine);
        newEntityBean.setNome(textService.isValid(nome) ? nome : null);

        return (ContinenteEntity) fixKey(newEntityBean);
    }

    @Override
    //casting only dalla superclasse
    public List<ContinenteEntity> findAll() {
        return super.findAll();
    }

    @Override
    //casting only dalla superclasse
    public ContinenteEntity findByKey(final Object keyPropertyValue) {
        return (ContinenteEntity) super.findByKey(keyPropertyValue);
    }

    /**
     * Metodo invocato:
     * 1) alla partenza del programma (facoltativo) <br>
     * 2) dal bottone resetDelete della classe xxxList <br>
     * La collection deve essere vuota <br>
     * DEVE essere sovrascritto, SENZA invocare il metodo della superclasse <br>
     *
     * @return enumeration di possibili risultati
     */
    @Override
    public RisultatoReset reset() {
        ContinenteEntity newBean;
        String message;

        for (ContinenteEnum contEnum : ContinenteEnum.values()) {
            newBean = newEntity(contEnum.ordinal() + 1, contEnum.getTag());
            if (newBean != null) {
                mappaBeans.put(contEnum.getTag(), newBean);
            }
            else {
                message = String.format("La entity %s non è stata salvata", contEnum.getTag());
                logger.error(new WrapLog().exception(new AlgosException(message)).usaDb().type(TypeLog.startup));
            }
        }

        mappaBeans.values().stream().forEach(bean -> insertSave(bean));
        return RisultatoReset.vuotoMaCostruito;
    }


}// end of CrudService class
