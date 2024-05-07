package it.algos.geo.regione;

import it.algos.geo.stato.*;
import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.stereotype.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@AEntity(collectionName = "regione", usaResetStartup = true)
public class RegioneEntity extends OrdineEntity {


    @Indexed(unique = true)
    @AField(type = TypeField.text)
    private String sigla;

    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.wikiAnchor, headerText = "Nome", caption = "Nome")
    private String code;

    //    @DBRef //@todo perché non funziona?
    @AField(type = TypeField.linkDBRef, widthList = 14, linkClazz = StatoEntity.class)
    private StatoEntity stato;

    @AField(type = TypeField.linkWiki)
    private String pagina;

    @Indexed()
    @ASearch(type = TypeSearch.comboClazz)
    @AField(type = TypeField.enumType, enumClazz = TypeRegione.class, widthList = 20)
    private TypeRegione type;


    @Override
    public String toString() {
        return code;
    }

}// end of Entity class
