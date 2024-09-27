package it.algos.geo.regione;

import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.vbase.backend.entity.AbstractEntity;
import it.algos.vbase.backend.form.DefaultForm;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class RegioneForm<T extends AbstractEntity> extends DefaultForm<T> {


    public RegioneForm(T bean) {
        super(bean);
    }

}
