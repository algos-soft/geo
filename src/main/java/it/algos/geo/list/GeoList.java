package it.algos.geo.list;

import com.vaadin.flow.component.grid.*;
import it.algos.vbase.backend.boot.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.list.*;
import it.algos.vbase.ui.view.*;
import it.algos.vbase.ui.wrapper.*;
import org.springframework.data.domain.*;

/**
 * Project geo
 * Created by Algos
 * User: gac
 * Date: gio, 02-mag-2024
 * Time: 17:56
 */
public abstract class GeoList extends CrudList {

    //--non utilizzato. Serve SOLO per evitare un bug di IntelliJIDEA che segnala errore.
    public GeoList() {
        super();
    }

    /**
     * @param parentCrudView che crea questa istanza
     */
    public GeoList(final CrudView parentCrudView) {
        super(parentCrudView);
    }

    protected void fixPreferenze() {
        super.fixPreferenze();

        super.basicSort = Sort.by(Sort.Direction.ASC, FIELD_NAME_ORDINE);

        super.usaSelettoreColonne = true;
        super.usaVariantCompact = false;
        super.usaBottoneResetDelete = true;
        super.usaBottoneNew = false;
        super.usaBottoneEdit = false;
        super.usaBottoneShow = true;
        super.usaBottoneDeleteEntity = false;
    }


    protected void fixAdmin() {
        if (BaseVar.isAdmin) {
            message = "Se collegato come admin, visualizza il bottone ResetDelete";
            headerPlaceHolder.add(ASpan.text(message).rosso().small());
        }
    }

}
