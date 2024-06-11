package it.algos.geo.provincia;

import it.algos.geo.continente.ContinenteEntity;
import it.algos.geo.regione.*;
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
@Document(collection = "provincia")
@AEntity()
public class ProvinciaEntity extends OrdineEntity {

    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textStartsWith)
    @AFieldList(width = 6)
//    @AField(type = TypeField.wikiAnchor)
    private String sigla;

    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textStartsWith)
    @AFieldList(width = 14)
//    @AField(type = TypeField.wikiAnchor, anchorPrefix = "Provincia di ")
    private String nome;

    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textContains)
    @AFieldList(width = 24, headerText = "Ufficiale")
//    @AField(type = TypeField.wikiAnchor)
    private String nomeCompleto;

    @Indexed()
    @ASearch(type = TypeSearch.textStartsWith)
    @AFieldList(width = 5)
//    @AField(type = TypeField.text)
    private String cap;

    @DBRef()
    @ASearch(type = TypeSearch.comboClazz, linkClazz = RegioneEntity.class, comboPlaceHolder = "Regioni")
    @AFieldList(width = 10)
//    @AField(type = TypeField.linkDBRef, linkClazz = RegioneEntity.class)
    private RegioneEntity regione;

    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
