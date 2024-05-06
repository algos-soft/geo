package it.algos.geo.comune;

import it.algos.geo.provincia.*;
import it.algos.geo.regione.*;
import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@AEntity(collectionName = "comune")
public class ComuneEntity extends AbstractEntity {


    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.text, headerText = "Nome", caption = "Nome",widthList = 14)
    private String code;


    @AField(type = TypeField.linkDBRef, widthList = 10, linkClazz = ProvinciaEntity.class)
    private ProvinciaEntity provincia;

    @AField(type = TypeField.text, widthList = 5)
    private String cap;

    @AField(type = TypeField.linkDBRef, widthList = 10, linkClazz = RegioneEntity.class)
    private RegioneEntity regione;


    @Override
    public String toString() {
        return code;
    }

}// end of Entity class
