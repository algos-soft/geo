package it.algos.geo.continente;

import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import lombok.*;

@Data
@NoArgsConstructor
@AEntity(collectionName = "continente", searchPropertyName = "nome", sortPropertyName = "ordine")
public class ContinenteEntity extends AbstractEntity {

    @AField(type = TypeField.ordine, headerText = "#")
    private int ordine;

    @AField(type = TypeField.text)
    private String nome;


    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
