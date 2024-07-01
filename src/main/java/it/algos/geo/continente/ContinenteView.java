package it.algos.geo.continente;

import com.vaadin.flow.router.Route;
import it.algos.vbase.backend.annotation.AView;
import it.algos.vbase.ui.view.CrudView;
import it.algos.vbase.ui.view.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;

import static it.algos.geo.boot.GeoCost.GROUP_GEO;

/**
 * Project base24
 * Created by Algos
 * User: gac
 * Date: dom, 29-ott-2023
 * Time: 06:59
 *
 * @Route chiamata dal menu generale o dalla barra del browser <br>
 */
@Route(value = "continente", layout = MainLayout.class)
@AView(menuGroup = GROUP_GEO, menuName = "Continenti")
public class ContinenteView extends CrudView {

    /**
     * Costruttore invocato dalla sottoclasse concreta obbligatoriamente con (almeno) due parametri <br>
     * Mantiene il riferimento al CrudService (singleton) di questo Modulo, iniettato dalla sottoclasse concreta <br>
     * Mantiene il riferimento ad una listClazz (CrudList) per creare l'istanza prototype <br>
     */
    ContinenteView(@Autowired ContinenteService moduloService) {
        super(moduloService, ContinenteList.class);
    }

}// end of @Route CrudView class
