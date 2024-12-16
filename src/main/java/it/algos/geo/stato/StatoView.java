package it.algos.geo.stato;

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
 * Date: mer, 25-ott-2023
 * Time: 11:42
 *
 * @Route chiamata dal menu generale o dalla barra del browser <br>
 */
@Route(value = "stato", layout = MainLayout.class)
@IView(menuGroup = Gruppo.GEO, menuName = "Stati", vaadin = VaadinIcon.GLOBE)
public class StatoView extends AView {

    StatoView(@Autowired StatoService moduloService) {
        super(StatoEntity.class, moduloService, StatoList.class, StatoForm.class);
    }

}// end of @Route CrudView class
