package it.algos.geo.stato;

import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.vbase.backend.entity.AbstractEntity;
import it.algos.vbase.backend.enumeration.CrudOperation;
import it.algos.vbase.backend.list.AList;
import it.algos.vbase.ui.form.CrudForm;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class StatoForm extends CrudForm {


    //--new entityBean and update existing entityBean
    public StatoForm(final AList parentAList, AbstractEntity entityBean, CrudOperation operation) {
        super(parentAList, entityBean, operation);
    }


    @Override
    protected void override() {
        //--qui eventuali regolazioni specifiche delle variabili
    }

}// end of CrudForm class
