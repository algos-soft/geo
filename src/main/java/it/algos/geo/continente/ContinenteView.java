package it.algos.geo.continente;

import com.vaadin.flow.router.*;
import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.ui.view.*;
import org.springframework.beans.factory.annotation.*;

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
@AView(menuName = "Continenti", menuGroup = MenuGroup.geografia)
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
