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
@AEntity(collectionName = "comune", keyPropertyName = "nome", typeList = TypeList.hardWiki)
public class ComuneEntity extends AbstractEntity {

    @AField(type = TypeField.text, widthList = 14)
    private String nome;

    @AField(type = TypeField.linkDBRef, widthList = 10, linkClazz = ProvinciaEntity.class)
    private ProvinciaEntity provincia;

    @AField(type = TypeField.text, widthList = 5)
    private String cap;

    @AField(type = TypeField.linkDBRef, widthList = 10, linkClazz = RegioneEntity.class)
    private RegioneEntity regione;

    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
