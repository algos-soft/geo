package it.algos.geo.logic;

import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.logic.*;
import it.algos.vbase.backend.service.*;
import org.bson.types.*;

import javax.inject.*;

/**
 * Project geo
 * Created by Algos
 * User: gac
 * Date: sab, 11-mag-2024
 * Time: 07:18
 */
public abstract class GeoModuloService <T extends AbstractEntity> extends ModuloService<T> {

    @Inject
    protected ResourceService resourceService;

    @Inject
    protected WebService webService;

    public GeoModuloService(final Class entityClazz, final Class viewClazz) {
        super(entityClazz, viewClazz);
    }


    @Override
    public ObjectId getObjectId(T newEntityBean) {
        return getSubObjectId(newEntityBean);
    }

    protected ObjectId getSubObjectId(AbstractEntity newEntityBean) {
        return null;
        //        String keyPropertyName = annotationService.getKeyPropertyName(moduloCrudEntityClazz);
        //        String keyPropertyValue;
        //        String idTextValue12Char;
        //        ObjectId objectId = null;
        //
        //        if (textService.isValid(keyPropertyName) && !keyPropertyName.equals(FIELD_NAME_ID_CON)) {
        //            try {
        //                keyPropertyValue = reflectionService.getPropertyValueStr(newEntityBean, keyPropertyName);
        //                idTextValue12Char = textService.fixSize(keyPropertyValue, ID_LENGTH);
        //                objectId = new ObjectId(idTextValue12Char.getBytes());
        //            } catch (Exception unErrore) {
        //                Object alfa = newEntityBean;
        //                keyPropertyValue = reflectionService.getPropertyValueStr(newEntityBean, keyPropertyName);
        //                idTextValue12Char = textService.fixSize(keyPropertyValue, ID_LENGTH);
        //                logger.info(new WrapLog().exception(unErrore).usaDb());
        //            }
        //        }
        //
        //        return objectId;
    }

    //    protected ObjectId getObjectId(final String idStringValue) {
    //        String idTextValue12Char;
    //        ObjectId objectId = null;
    //
    //        try {
    //            if (textService.isValid(idStringValue)) {
    //                idTextValue12Char = textService.fixSize(idStringValue, ID_LENGTH);
    //                idTextValue12Char = StringUtils.stripAccents(idTextValue12Char);
    //                objectId = new ObjectId(idTextValue12Char.getBytes());
    //            }
    //
    //        } catch (Exception unErrore) {
    //            logger.error(new WrapLog().message(idStringValue));
    //        }
    //
    //        return objectId;
    //    }
    //
    //    //--eventuale - da discutere
    //    public AbstractEntity findById(final String idStringValue) {
    //        return findById(new ObjectId(textService.fixSize(idStringValue, ID_LENGTH).getBytes()));
    //    }


}
