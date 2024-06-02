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
@AEntity()
public class StatoEntity extends OrdineEntity {


    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.wikiAnchor)
    private String nome;

    @Indexed()
    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.wikiAnchor)
    private String capitale;

    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textContains)
    @AFieldList(width = 6)
    @AField(type = TypeField.text)
    private String alfa3;

    @Indexed(unique = true)
    @AFieldList(width = 6)
    @AField(type = TypeField.text)
    private String alfa2;

    @Indexed(unique = true)
    @AFieldList(width = 6)
    @AField(type = TypeField.text, headerText = "cod.")
    private String numerico;

    @AField(type = TypeField.linkWiki)
    private String divisioni;

    @DBRef()
    @ASearch(type = TypeSearch.comboClazz, comboStartProperty = "code", comboStartValue = "Europa")
    @AFieldList(width = 14)
    @AField(type = TypeField.linkDBRef, linkClazz = ContinenteEntity.class)
    private ContinenteEntity continente;

    @ASearch(type = TypeSearch.checkBox, typeCheckIniziale = TypeCheckBox.vero)
    @AField(type = TypeField.booleano,headerText = "UE")
    private boolean unioneEuropea;

    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
