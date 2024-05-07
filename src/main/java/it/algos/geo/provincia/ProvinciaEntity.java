package it.algos.geo.provincia;

import it.algos.geo.regione.*;
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
@AEntity(collectionName = "provincia", usaResetStartup = true)
public class ProvinciaEntity extends OrdineEntity {

    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.wikiAnchor, widthList = 5, headerText = "Sigla", caption = "Sigla")
    private String code;

    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.wikiAnchor, widthList = 12, anchorPrefix = "Provincia di ")
    private String nome;

    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textContains)
    @AField(type = TypeField.wikiAnchor, headerText = "Ufficiale", widthList = 24)
    private String nomeCompleto;

    @Indexed()
    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.text, widthList = 5)
    private String cap;

    @Indexed()
    @ASearch(type = TypeSearch.comboClazz)
    @AField(type = TypeField.linkDBRef, widthList = 10, linkClazz = RegioneEntity.class)
    private RegioneEntity regione;

    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
