package it.algos.geo.continente;

import it.algos.vbase.annotation.*;
import it.algos.vbase.entity.OrdineEntity;
import lombok.*;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "continente")
@ILog()
@IReset()
@IEntity(sortProperty = "ordine")
public class ContinenteEntity extends OrdineEntity {


    @Indexed(unique = true)
    @IFieldSearch
    @IFieldList()
    private String nome;


    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
