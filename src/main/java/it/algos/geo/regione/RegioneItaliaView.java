package it.algos.geo.regione;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import it.algos.vbase.backend.annotation.AView;
import it.algos.vbase.backend.constant.Gruppo;
import it.algos.vbase.ui.view.CrudView;
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
@AView(menuGroup = Gruppo.GEO, menuName = "Regioni italiane", vaadin = VaadinIcon.GLOBE)
public class RegioneItaliaView extends CrudView {

    /**
     * Costruttore alternativo invocato dalla sottoclasse concreta se si usa anche una formClazz specifico <br>
     * Mantiene il riferimento al CrudService Service (singleton) di questo Modulo <br>
     * Mantiene il riferimento ad una listClazz (CrudList) per creare l'istanza prototype <br>
     * Mantiene il riferimento ad una formClazz (CrudForm) per creare l'istanza prototype <br>
     */
    public RegioneItaliaView(@Autowired RegioneService moduloService) {
        super(moduloService, RegioneItaliaList.class, RegioneForm.class);
    }

}// end of @Route CrudView class
