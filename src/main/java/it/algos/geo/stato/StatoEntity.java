package it.algos.geo.stato;

import it.algos.geo.continente.ContinenteEntity;
import it.algos.vbase.annotation.*;
import it.algos.vbase.entity.OrdineEntity;
import it.algos.vbase.enumeration.TextSearchMode;
import it.algos.vbase.enumeration.CheckBoxStatus;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "stato")
@ILog()
@IReset()
@IEntity(sortProperty = "nome")
public class StatoEntity extends OrdineEntity {


    @Indexed(unique = true)
    @ISearch
    @IFieldList()
    private String nome;

    @Indexed()
    @ISearch
    @IFieldList()
//    @AField(type = TypeField.wikiAnchor)
    private String capitale;

    @Indexed(unique = true)
    @ISearch(textSearchMode = TextSearchMode.contains)
    @IFieldList(width = 6)
//    @AField(type = TypeField.text)
    private String alfa3;

    @Indexed(unique = true)
    @IFieldList(width = 6)
//    @AField(type = TypeField.text)
    private String alfa2;

    @Indexed(unique = true)
    @IFieldList(width = 6, headerText = "cod.")
//    @AField(type = TypeField.text)
    private String numerico;

    @IFieldList()
//    @AField(type = TypeField.linkWiki)
    private String divisioni;

    @DBRef
    @ISearch(placeholder = "Continenti")
    @IFieldList(width = 14)
    private ContinenteEntity continente;


    @ISearch(checkBoxInitialStatus = CheckBoxStatus.vero)
    @IFieldList(headerText = "UE")
//    @AField(type = TypeField.booleano)
    private boolean unioneEuropea;

    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
