package it.algos.geo.continente;

import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AEntity(collectionName = "continente", sortPropertyName = "ordine")
public class ContinenteEntity extends AbstractEntity {

    @AField(type = TypeField.ordine, headerText = "#")
    private int ordine;

    @ASearch()
    @AField(type = TypeField.text,headerText = "nome")
    private String code;


    @Override
    public String toString() {
        return code;
    }

}// end of Entity class
