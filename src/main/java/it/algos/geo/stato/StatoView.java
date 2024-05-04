package it.algos.geo.stato;

import com.vaadin.flow.router.*;
import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.ui.view.*;
import org.springframework.beans.factory.annotation.*;

/**
 * Project base24
 * Created by Algos
 * User: gac
 * Date: mer, 25-ott-2023
 * Time: 11:42
 *
 * @Route chiamata dal menu generale o dalla barra del browser <br>
 */
@PageTitle("Stati")
@Route(value = "stato", layout = MainLayout.class)
@AView(menuGroup = MenuGroup.geografia)
public class StatoView extends CrudView {

    /**
     * Costruttore alternativo invocato dalla sottoclasse concreta se si usa anche una formClazz specifico <br>
     * Mantiene il riferimento al CrudService Service (singleton) di questo Modulo <br>
     * Mantiene il riferimento ad una listClazz (CrudList) per creare l'istanza prototype <br>
     * Mantiene il riferimento ad una formClazz (CrudForm) per creare l'istanza prototype <br>
     */
    public StatoView(@Autowired StatoService moduloCrudService) {
        super(moduloCrudService, StatoList.class, StatoForm.class);
    }

}// end of @Route CrudView class