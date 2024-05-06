package it.algos.geo.continente;

import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import lombok.*;
import org.springframework.data.mongodb.core.index.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AEntity(collectionName = "continente", usaResetStartup = true, sortPropertyName = "ordine")
public class ContinenteEntity extends AbstractEntity {

    @Indexed(unique = true)
    @AField(type = TypeField.ordine, headerText = "#")
    private int ordine;

    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.text, headerText = "Nome", caption = "Nome")
    private String code;


    @Override
    public String toString() {
        return code;
    }

}// end of Entity class
