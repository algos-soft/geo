package it.algos.geo.regione;

import it.algos.geo.stato.*;
import it.algos.vbase.annotation.IEntity;
import it.algos.vbase.annotation.IFieldList;
import it.algos.vbase.annotation.IReset;
import it.algos.vbase.annotation.ISearch;
import it.algos.vbase.entity.OrdineEntity;
import it.algos.vbase.enumeration.TypeRegione;
import lombok.*;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "regione")
@IReset()
@IEntity()
public class RegioneEntity extends OrdineEntity {


    @Indexed(unique = true)
    @ISearch
    @IFieldList(width = 6)
    private String sigla;

    @Indexed(unique = true)
    @ISearch
    private String nome;

    @DBRef()
    @ISearch(placeholder = "Stati")
    @IFieldList(width = 14)
    private StatoEntity stato;

    @IFieldList()
    private String pagina;

    @Indexed()
    @ISearch
    @IFieldList(width = 20)
    private TypeRegione type;


    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
