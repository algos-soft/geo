package it.algos.geo.provincia;

import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.vbase.entity.AbstractEntity;
import it.algos.vbase.form.DefaultForm;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class ProvinciaForm<T extends AbstractEntity> extends DefaultForm<T> {


    public ProvinciaForm(T bean) {
        super(bean);
    }

}
