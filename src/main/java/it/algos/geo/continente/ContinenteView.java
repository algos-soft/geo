package it.algos.geo.continente;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import it.algos.vbase.annotation.IView;
import it.algos.vbase.constant.Gruppo;
import it.algos.vbase.ui.view.AView;
import it.algos.vbase.ui.view.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;


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
@IView(menuGroup = Gruppo.GEO, menuName = "Continenti", vaadin = VaadinIcon.GLOBE)
public class ContinenteView extends AView {

    /**
     * Costruttore invocato dalla sottoclasse concreta obbligatoriamente con (almeno) due parametri <br>
     * Mantiene il riferimento al CrudService (singleton) di questo Modulo, iniettato dalla sottoclasse concreta <br>
     * Mantiene il riferimento ad una listClazz (AList) per creare l'istanza prototype <br>
     */
    ContinenteView(@Autowired ContinenteService moduloService) {
        super(ContinenteEntity.class, moduloService, ContinenteList.class);
    }

}// end of @Route CrudView class
