package it.algos.geo.stato;

import com.vaadin.flow.spring.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.backend.list.*;
import it.algos.vbase.ui.form.*;
import static org.springframework.beans.factory.config.BeanDefinition.*;
import org.springframework.context.annotation.*;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class StatoForm extends CrudForm {


    //--non utilizzato. Serve SOLO per evitare un bug di IntelliJIDEA che segnala errore.
    public StatoForm() {
        super();
    }

    //--new entityBean and update existing entityBean
    public StatoForm(final AList parentAList, AbstractEntity entityBean, CrudOperation operation) {
        super(parentAList, entityBean, operation);
    }


    @Override
    protected void override() {
        //--qui eventuali regolazioni specifiche delle variabili
    }

}// end of CrudForm class
