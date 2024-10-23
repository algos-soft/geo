package it.algos.geo.provincia;

import it.algos.geo.regione.RegioneEntity;
import it.algos.vbase.annotation.AEntity;
import it.algos.vbase.annotation.AFieldForm;
import it.algos.vbase.annotation.AFieldList;
import it.algos.vbase.annotation.ASearch;
import it.algos.vbase.entity.OrdineEntity;
import it.algos.vbase.enumeration.TBool;
import it.algos.vbase.enumeration.TextSearchMode;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "provincia")
@AEntity()
public class ProvinciaEntity extends OrdineEntity {

    @Indexed(unique = true)
    @ASearch(textSearchMode = TextSearchMode.startsWith)
    @AFieldList(width = 6)
    private String sigla;

    @Indexed(unique = true)
    @ASearch
    @AFieldList(width = 14)
//    @AField(type = TypeField.wikiAnchor, anchorPrefix = "Provincia di ")
    private String nome;

    @Indexed(unique = true)
    @ASearch(textSearchMode = TextSearchMode.contains)
    @AFieldList(width = 24, headerText = "Ufficiale")
    private String nomeCompleto;

    @Indexed()
    @ASearch
    @AFieldList(width = 5)
    private String cap;

    @DBRef()
//    @ARef(linkClazz = RegioneService.class, linkedProperty = "nome")
    @ASearch(placeholder = "Regioni")
    @AFieldList(width = 10)
//    @AFieldForm(linkedProperty = "sigla", clearButtonVisible  = TBool.falso)
    @AFieldForm( clearButtonVisible  = TBool.falso)
    private RegioneEntity regione;

    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
