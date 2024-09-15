package it.algos.geo.comune;

import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.vbase.backend.form.AForm;
import it.algos.vbase.backend.logic.ModuloService;
import lombok.NonNull;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class ComuneForm extends AForm<ComuneEntity> {


//    //--non utilizzato. Serve SOLO per evitare un bug di IntelliJIDEA che segnala errore.
//    public ComuneForm() {
//        super();
//    }
//
//    //--new entityBean and update existing entityBean
//    public ComuneForm(final CrudList parentCrudList, AbstractEntity entityBean, CrudOperation operation) {
//        super(parentCrudList, entityBean, operation);
//    }

    public ComuneForm(@NonNull ModuloService<ComuneEntity> moduloService, ComuneEntity bean) {
        super(moduloService, bean);
    }

}// end of CrudForm class
