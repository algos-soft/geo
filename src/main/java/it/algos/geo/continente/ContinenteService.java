package it.algos.geo.continente;

import it.algos.geo.enumeration.*;
import it.algos.geo.logic.*;
import it.algos.vbase.enumeration.RisultatoReset;
import org.bson.types.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

import static it.algos.vbase.boot.BaseCost.ID_LENGTH;

/**
 * Project base24
 * Created by Algos
 * User: gac
 * Date: dom, 29-ott-2023
 * Time: 06:59
 */
@Service
public class ContinenteService extends GeoModuloService<ContinenteEntity> {

    @Value("${algos.project.usa.dir.geo:true}")
    private boolean usaDirGeo;


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
     * @param ordine (opzionale, unico)
     * @param nome   (obbligatorio, unico)
     *
     * @return la nuova entity appena creata (con keyID ma non salvata)
     */
    public ContinenteEntity newEntity(final int ordine, final String nome) {
        ContinenteEntity newEntityBean = ContinenteEntity.builder()
                .nome(textService.isValid(nome) ? nome : null)
                .build();

        newEntityBean.setOrdine(ordine == 0 ? nextOrdine() : ordine);
        return newEntityBean;
    }

    @Override
    public ObjectId getObjectId(ContinenteEntity newEntityBean) {
        return new ObjectId(textService.fixSize(((ContinenteEntity) newEntityBean).getNome(), ID_LENGTH).getBytes());
    }

    @Override
    public ContinenteEntity findById(final String idStringValue) {
        return (ContinenteEntity) super.findById(idStringValue);
    }

    @Override
    public List<ContinenteEntity> findAll() {
        return super.findAll();
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
        int ordine;
        String code;

        if (!usaDirGeo) {
            return RisultatoReset.nonCostruito;
        }

        for (ContinenteEnum contEnum : ContinenteEnum.values()) {
            ordine = contEnum.ordinal() + 1;
            code = contEnum.getTag();
            newBean = newEntity(ordine, code);

            if (newBean != null) {
                mappaBeans.put(code, newBean);
            }
        }

        mappaBeans.values().stream().forEach(bean -> creaIfNotExists(bean));
        return RisultatoReset.vuotoMaCostruito;
    }


}// end of CrudService class
