package it.algos.geo.stato;

import it.algos.geo.continente.*;
import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import lombok.*;
import org.springframework.data.mongodb.core.index.*;

@Data
@NoArgsConstructor
@AEntity(collectionName = "stato", usaResetStartup = true)
public class StatoEntity extends AbstractEntity {

    @Indexed(unique = true)
    @AField(type = TypeField.ordine, headerText = "#")
    private int ordine;

    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.wikiAnchor, headerText = "Nome", caption = "Nome")
    private String code;

    @Indexed()
    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.wikiAnchor)
    private String capitale;

    @Indexed(unique = true)
    //    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.text, widthList = 6)
    private String alfa3;

    @Indexed(unique = true)
    //    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.text, widthList = 6)
    private String alfa2;

    @Indexed(unique = true)
    //    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.text, headerText = "cod.", widthList = 6)
    private String numerico;

    @AField(type = TypeField.linkWiki)
    private String divisioni;

    @Indexed()
    @ASearch(type = TypeSearch.comboClazz, comboStartProperty = "code", comboStartValue = "Europa")
    @AField(type = TypeField.linkDBRef, widthList = 14, linkClazz = ContinenteEntity.class)
    private ContinenteEntity continente;


    @Override
    public String toString() {
        return code;
    }

}// end of Entity class
