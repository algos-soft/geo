package it.algos.geo.stato;

import it.algos.geo.continente.ContinenteEntity;
import it.algos.vbase.annotation.*;
import it.algos.vbase.entity.OrdineEntity;
import it.algos.vbase.enumeration.TextSearchMode;
import it.algos.vbase.enumeration.TriState;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "stato")
@ILog()
@IReset()
@IEntity(sortProperty = "nome")
public class StatoEntity extends OrdineEntity {


    @Indexed(unique = true)
    @IFieldSearch
    @IFieldList()
    private String nome;

    @Indexed()
    @IFieldSearch
    @IFieldList()
    private String capitale;

    @Indexed(unique = true)
    @IFieldSearch(textSearchMode = TextSearchMode.contains)
    @IFieldList(width = 6)
    private String alfa3;

    @Indexed(unique = true)
    @IFieldList(width = 6)
    private String alfa2;

    @Indexed(unique = true)
    @IFieldList(width = 6, headerText = "cod.")
    private String numerico;

    @IFieldList()
    private String divisioni;

    @DBRef
    @IFieldSearch(placeholder = "Continenti")
    @IFieldList(width = 14)
    private ContinenteEntity continente;


    @IFieldSearch(checkBoxInitialStatus = TriState.vero)
    @IFieldList(headerText = "UE")
    private boolean unioneEuropea;

    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
