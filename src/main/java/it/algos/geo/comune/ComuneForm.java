package it.algos.geo.comune;

import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.vbase.backend.form.DefaultForm;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class ComuneForm extends DefaultForm<ComuneEntity> {

    public ComuneForm() {
        super();
    }

    public ComuneForm(ComuneEntity bean) {
        super(bean);
    }


}// end of CrudForm class
