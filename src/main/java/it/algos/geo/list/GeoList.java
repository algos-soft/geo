package it.algos.geo.list;

import it.algos.vbase.backend.boot.BaseVar;
import it.algos.vbase.backend.list.CrudList;
import it.algos.vbase.ui.view.CrudView;
import it.algos.vbase.ui.wrapper.ASpan;

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

        super.usaSelettoreColonne = true;
        super.usaVariantCompact = false;
//        super.usaBottoneResetDelete = true;
//        super.usaBottoneNew = false;
//        super.usaBottoneEdit = false;
//        super.usaBottoneShow = true;
//        super.usaBottoneDeleteEntity = false;
//
//        super.usaBottoneExport = true;
    }


    protected void fixAdmin() {
        if (BaseVar.isAdmin) {
            message = "Se collegato come admin, visualizza il bottone ResetDelete";
            headerPlaceHolder.add(ASpan.text(message).rosso().small());
        }
    }

}
