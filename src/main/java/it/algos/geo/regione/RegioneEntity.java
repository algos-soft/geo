package it.algos.geo.regione;

import it.algos.geo.stato.*;
import it.algos.vbase.annotation.IEntity;
import it.algos.vbase.annotation.IFieldList;
import it.algos.vbase.annotation.IReset;
import it.algos.vbase.annotation.IFieldSearch;
import it.algos.vbase.entity.OrdineEntity;
import it.algos.vbase.enumeration.TypeRegione;
import lombok.*;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "regione")
@IReset()
@IEntity()
public class RegioneEntity extends OrdineEntity {


    @Indexed(unique = true)
    @IFieldSearch
    @IFieldList(width = 6)
    private String sigla;

    @Indexed(unique = true)
    @IFieldSearch
    private String nome;

    @DBRef()
    @IFieldSearch(placeholder = "Stati")
    @IFieldList(width = 14)
    private StatoEntity stato;

    @IFieldList()
    private String pagina;

    @Indexed()
    @IFieldSearch
    @IFieldList(width = 20)
    private TypeRegione type;


    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
