package it.algos.geo.continente;

import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import lombok.*;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "continente")
@ALog()
@AReset()
@AEntity(sort = "ordine")
public class ContinenteEntity extends OrdineEntity {


    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textStartsWith)
    @AFieldList()
    private String nome;


    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
