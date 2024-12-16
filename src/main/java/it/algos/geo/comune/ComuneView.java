package it.algos.geo.comune;

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
 * Time: 09:13
 *
 * @Route chiamata dal menu generale oppure dalla barra del browser <br>
 */
@Route(value = "comune", layout = MainLayout.class)
@IView(menuGroup = Gruppo.GEO, menuName = "Comuni", vaadin = VaadinIcon.GLOBE)
public class ComuneView extends AView {


    ComuneView(@Autowired ComuneService moduloService) {
        super(ComuneEntity.class, moduloService, ComuneList.class, ComuneForm.class);
    }

}// end of @Route CrudView class
