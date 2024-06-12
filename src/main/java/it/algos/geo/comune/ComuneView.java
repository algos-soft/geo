package it.algos.geo.comune;

import com.vaadin.flow.router.*;
import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.ui.view.*;
import org.springframework.beans.factory.annotation.*;

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
@AView(menuName = "Comuni", menuGroup = MenuGroup.geografia)
public class ComuneView extends CrudView {


    /**
     * Costruttore alternativo invocato dalla sottoclasse concreta se si usa anche una formClazz specifico <br>
     * Mantiene il riferimento al CrudService Service (singleton) di questo Modulo <br>
     * Mantiene il riferimento ad una listClazz (CrudList) per creare l'istanza prototype <br>
     * Mantiene il riferimento ad una formClazz (CrudForm) per creare l'istanza prototype <br>
     */
    ComuneView(@Autowired ComuneService moduloService) {
        super(moduloService, ComuneList.class, ComuneForm.class);
    }

}// end of @Route CrudView class
