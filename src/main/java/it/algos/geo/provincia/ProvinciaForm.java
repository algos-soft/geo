package it.algos.geo.provincia;

import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.vbase.backend.form.DefaultForm;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class ProvinciaForm extends DefaultForm<ProvinciaEntity> {


    public ProvinciaForm(ProvinciaEntity bean) {
        super(bean);
    }

}// end of CrudForm class
