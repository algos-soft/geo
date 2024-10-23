package it.algos.geo.continente;

import it.algos.vbase.annotation.*;
import it.algos.vbase.entity.OrdineEntity;
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
@AEntity(sortProperty = "ordine")
public class ContinenteEntity extends OrdineEntity {


    @Indexed(unique = true)
    @ASearch
    @AFieldList()
    private String nome;


    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
