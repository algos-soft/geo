package it.algos.geo.continente;

import com.vaadin.flow.spring.annotation.*;
import it.algos.geo.list.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.boot.*;
import it.algos.vbase.backend.list.*;
import it.algos.vbase.ui.wrapper.*;
import static org.springframework.beans.factory.config.BeanDefinition.*;
import org.springframework.context.annotation.*;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class ContinenteList extends GeoList {

    //--non utilizzato. Serve SOLO per evitare un bug di IntelliJIDEA che segnala errore.
    public ContinenteList() {
        super();
    }

    /**
     * @param parentCrudView che crea questa istanza
     */
    public ContinenteList(final ContinenteView parentCrudView) {
        super(parentCrudView);
    }



    @Override
    public void syncHeader() {
        String enumeration = "ContinenteEnum";
        String service = "Continente";

        super.infoScopo = String.format(TEXT_TAVOLA + SPAZIO + TEXT_ENUM, enumeration, service); ;
        super.infoCreazione = TEXT_HARD;
        super.infoReset = TEXT_HARD_2;

        super.fixHeaderPost();
        super.fixAdmin();
    }


}// end of CrudList class
