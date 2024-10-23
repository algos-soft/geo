package it.algos.geo.provincia;

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
 * Date: Sat, 03-Feb-2024
 * Time: 09:48
 *
 * @Route chiamata dal menu generale o dalla barra del browser <br>
 */
@Route(value = "provincia", layout = MainLayout.class)
@IView(menuGroup = Gruppo.GEO, menuName = "Province italiane", vaadin = VaadinIcon.GLOBE)
public class ProvinciaView extends AView {


    ProvinciaView(@Autowired ProvinciaService moduloService) {
        super(moduloService, ProvinciaList.class, ProvinciaForm.class);
    }

}// end of @Route CrudView class
