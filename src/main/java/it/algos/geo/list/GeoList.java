package it.algos.geo.list;

import it.algos.vbase.backend.boot.BaseVar;
import it.algos.vbase.backend.list.AList;
import it.algos.vbase.ui.view.AView;
import it.algos.vbase.ui.wrapper.ASpan;

/**
 * Project geo
 * Created by Algos
 * User: gac
 * Date: gio, 02-mag-2024
 * Time: 17:56
 */
public abstract class GeoList extends AList {
    protected String infoCreazione;

    protected String infoReset;


    /**
     * @param parentCrudView che crea questa istanza
     */
    public GeoList(final AView parentCrudView) {
        super(parentCrudView);
    }

    protected void fixPreferenze() {
        super.fixPreferenze();
    }


    protected void fixAdmin() {
        if (BaseVar.isAdmin) {
            message = "Se collegato come admin, visualizza il bottone ResetDelete";
            headerPlaceHolder.add(ASpan.text(message).rosso().small());
        }
    }

}
