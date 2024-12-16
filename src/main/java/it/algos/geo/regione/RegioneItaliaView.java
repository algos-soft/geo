package it.algos.geo.regione;

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
 * Date: Tue, 07-Nov-2023
 * Time: 16:47
 *
 * @Route chiamata dal menu generale o dalla barra del browser <br>
 */
@Route(value = "regioneitaliana", layout = MainLayout.class)
@IView(menuGroup = Gruppo.GEO, menuName = "Regioni italiane", vaadin = VaadinIcon.GLOBE)
public class RegioneItaliaView extends AView {

    public RegioneItaliaView(@Autowired RegioneService moduloService) {
        super(RegioneEntity.class, moduloService, RegioneItaliaList.class, RegioneForm.class);
    }

}// end of @Route CrudView class
