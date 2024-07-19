package it.algos.geo.stato;

import it.algos.geo.continente.*;
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
@Document(collection = "stato")
@ALog()
@AReset()
@AEntity(sort = "ordine")
public class StatoEntity extends OrdineEntity {


    @Indexed(unique = true)
    @ASearch
    @AFieldList()
    private String nome;

    @Indexed()
    @ASearch
    @AFieldList()
//    @AField(type = TypeField.wikiAnchor)
    private String capitale;

    @Indexed(unique = true)
    @ASearch(textSearchMode = TextSearchMode.contains)
    @AFieldList(width = 6)
//    @AField(type = TypeField.text)
    private String alfa3;

    @Indexed(unique = true)
    @AFieldList(width = 6)
//    @AField(type = TypeField.text)
    private String alfa2;

    @Indexed(unique = true)
    @AFieldList(width = 6,headerText = "cod.")
//    @AField(type = TypeField.text)
    private String numerico;

    @AFieldList()
//    @AField(type = TypeField.linkWiki)
    private String divisioni;

    @DBRef
    @ASearch(type = TypeSearch.comboClazz, linkClazz = ContinenteEntity.class, comboPlaceHolder = "Continenti")
    @AFieldList(width = 14)
    private ContinenteEntity continente;


    @ASearch(type = TypeSearch.checkBox, checkBoxInitialStatus = TypeCheckBox.vero)
    @AFieldList(headerText = "UE")
//    @AField(type = TypeField.booleano)
    private boolean unioneEuropea;

    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
