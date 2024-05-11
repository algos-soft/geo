package it.algos.geo.comune;

import it.algos.geo.provincia.*;
import it.algos.geo.regione.*;
import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import lombok.*;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "comune")
@AEntity(usaResetStartup = true)
public class ComuneEntity extends OrdineEntity {


    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.wikiAnchor, headerText = "Nome", caption = "Nome", widthList = 18)
    private String code;

    @DBRef()
    @ASearch(type = TypeSearch.comboClazz)
    @AField(type = TypeField.linkDBRef, widthList = 10, linkClazz = ProvinciaEntity.class)
    private ProvinciaEntity provincia;

    @Indexed()
    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.text, widthList = 6)
    private String cap;

    @DBRef()
    @ASearch(type = TypeSearch.comboClazz)
    @AField(type = TypeField.linkDBRef, widthList = 10, linkClazz = RegioneEntity.class)
    private RegioneEntity regione;


    @Override
    public String toString() {
        return code;
    }

}// end of Entity class
