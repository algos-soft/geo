package it.algos.geo.comune;

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
 * Date: Sat, 03-Feb-2024
 * Time: 09:13
 *
 * @Route chiamata dal menu generale oppure dalla barra del browser <br>
 */
@Route(value = "comune", layout = MainLayout.class)
@AView(menuGroup = Gruppo.GEO, menuName = "Comuni", vaadin = VaadinIcon.GLOBE)
public class ComuneView extends CrudView {


    /**
     * Costruttore alternativo invocato dalla sottoclasse concreta se si usa anche una formClazz specifico <br>
     * Mantiene il riferimento al CrudService Service (singleton) di questo Modulo <br>
     * Mantiene il riferimento ad una listClazz (AList) per creare l'istanza prototype <br>
     * Mantiene il riferimento ad una formClazz (CrudForm) per creare l'istanza prototype <br>
     */
    ComuneView(@Autowired ComuneService moduloService) {
        super(moduloService, ComuneList.class, ComuneForm.class);
    }

}// end of @Route CrudView class
