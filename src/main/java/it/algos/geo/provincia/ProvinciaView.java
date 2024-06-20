package it.algos.geo.provincia;

import com.vaadin.flow.router.*;
import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.constant.Gruppo;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.ui.view.*;
import org.springframework.beans.factory.annotation.*;

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
@AView(menuName = "Province italiane", menuGroup = Gruppo.GEO)
public class ProvinciaView extends CrudView {


    /**
     * Costruttore alternativo invocato dalla sottoclasse concreta se si usa anche una formClazz specifico <br>
     * Mantiene il riferimento al CrudService Service (singleton) di questo Modulo <br>
     * Mantiene il riferimento ad una listClazz (CrudList) per creare l'istanza prototype <br>
     * Mantiene il riferimento ad una formClazz (CrudForm) per creare l'istanza prototype <br>
     */
    ProvinciaView(@Autowired ProvinciaService moduloService) {
        super(moduloService, ProvinciaList.class, ProvinciaForm.class);
    }

}// end of @Route CrudView class
