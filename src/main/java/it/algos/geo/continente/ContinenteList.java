package it.algos.geo.continente;

import com.vaadin.flow.spring.annotation.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.list.*;
import static org.springframework.beans.factory.config.BeanDefinition.*;
import org.springframework.context.annotation.*;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class ContinenteList extends CrudList {

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
    protected void override() {
        super.usaBottoneShow = false;
    }


    @Override
    public void syncHeader() {
        String enumeration = "Continente";

        super.infoScopo = String.format(TEXT_TAVOLA + SPAZIO + TEXT_ENUM, enumeration, enumeration); ;
        super.infoCreazione = TEXT_HARD;
        super.infoReset = TEXT_RESET_DELETE;

        super.fixHeaderPost();
    }


}// end of CrudList class
