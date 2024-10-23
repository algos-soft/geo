package it.algos.geo.comune;

import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.vbase.entity.AbstractEntity;
import it.algos.vbase.form.DefaultForm;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class ComuneForm<T extends AbstractEntity> extends DefaultForm<T> {


    public ComuneForm(T bean) {
        super(bean);
    }


}
