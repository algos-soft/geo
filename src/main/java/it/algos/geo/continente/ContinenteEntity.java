package it.algos.geo.continente;

import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import lombok.*;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "continente")
//@AEntity( keyPropertyName = "code", sortPropertyName = "ordine", usaResetStartup = true)
public class ContinenteEntity extends OrdineEntity {


    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.text, headerText = "Nome", caption = "Nome")
    private String code;


    @Override
    public String toString() {
        return code;
    }

}// end of Entity class
