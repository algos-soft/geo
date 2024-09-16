package it.algos.geo.provincia;

import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.vbase.backend.form.AForm;
import it.algos.vbase.backend.logic.ModuloService;
import lombok.NonNull;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class ProvinciaForm extends AForm<ProvinciaEntity> {


    public ProvinciaForm(@NonNull ModuloService<ProvinciaEntity> moduloService, ProvinciaEntity bean) {
        super(moduloService, bean);
    }

}// end of CrudForm class
