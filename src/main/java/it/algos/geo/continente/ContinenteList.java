package it.algos.geo.continente;

import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.geo.list.GeoList;
import it.algos.vbase.backend.annotation.AViewList;
import org.springframework.context.annotation.Scope;

import static it.algos.vbase.backend.boot.BaseCost.*;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
@AViewList()
public class ContinenteList extends GeoList {


    /**
     * @param parentCrudView che crea questa istanza
     */
    public ContinenteList(final ContinenteView parentCrudView) {
        super(parentCrudView);
    }



    @Override
    public void fixHeader() {
        String enumeration = "ContinenteEnum";
        String service = "Continente";

        super.infoScopo = String.format(TEXT_TAVOLA + SPAZIO + TEXT_ENUM, enumeration, service); ;
        super.infoCreazione = TEXT_HARD;
        super.infoReset = TEXT_RESET_DELETE;

        super.fixHeader();
        super.fixAdmin();
    }


}// end of AList class
