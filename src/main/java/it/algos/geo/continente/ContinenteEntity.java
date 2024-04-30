package it.algos.geo.continente;

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
@AEntity(collectionName = "continente", typeList = TypeList.hardEnum)
public class ContinenteEntity extends AbstractEntity {

    @AField(type = TypeField.ordine, headerText = "#")
    private int ordine;

    @AField(type = TypeField.text, widthList = 12)
    private String nome;

    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
