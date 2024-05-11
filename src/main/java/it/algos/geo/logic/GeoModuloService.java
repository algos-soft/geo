package it.algos.geo.logic;

import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.logic.*;
import it.algos.vbase.backend.service.*;
import it.algos.vbase.backend.wrapper.*;
import org.bson.types.*;

import javax.inject.*;

/**
 * Project geo
 * Created by Algos
 * User: gac
 * Date: sab, 11-mag-2024
 * Time: 07:18
 */
public abstract class GeoModuloService extends ModuloService {

    @Inject
    protected WebService webService;

    public GeoModuloService(final Class entityClazz, final Class viewClazz) {
        super(entityClazz, viewClazz);
    }


    @Override
    public ObjectId getObjectId(AbstractEntity newEntityBean) {
        return getSubObjectId(newEntityBean);
    }

    protected ObjectId getSubObjectId(AbstractEntity newEntityBean) {
        String keyPropertyName = annotationService.getKeyPropertyName(moduloCrudEntityClazz);
        String keyPropertyValue;
        String idTextValue12Char;
        ObjectId objectId = null;

        if (textService.isValid(keyPropertyName) && !keyPropertyName.equals(FIELD_NAME_ID_CON)) {
            try {
                keyPropertyValue = reflectionService.getPropertyValueStr(newEntityBean, keyPropertyName);
                idTextValue12Char = textService.fixSize(keyPropertyValue, ID_LENGTH);
                objectId = new ObjectId(idTextValue12Char.getBytes());
            } catch (Exception unErrore) {
                Object alfa = newEntityBean;
                keyPropertyValue = reflectionService.getPropertyValueStr(newEntityBean, keyPropertyName);
                idTextValue12Char = textService.fixSize(keyPropertyValue, ID_LENGTH);
                logger.info(new WrapLog().exception(unErrore).usaDb());
            }
        }

        return objectId;
    }

}
