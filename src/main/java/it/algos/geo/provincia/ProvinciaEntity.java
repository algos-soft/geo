package it.algos.geo.provincia;

import it.algos.geo.regione.RegioneEntity;
import it.algos.vbase.annotation.*;
import it.algos.vbase.entity.OrdineEntity;
import it.algos.vbase.enumeration.TBool;
import it.algos.vbase.enumeration.TextSearchMode;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "provincia")
@IReset()
@IEntity()
public class ProvinciaEntity extends OrdineEntity {

    @Indexed(unique = true)
    @IFieldSearch(textSearchMode = TextSearchMode.startsWith)
    @IFieldList(width = 6)
    private String sigla;

    @Indexed(unique = true)
    @IFieldSearch
    @IFieldList(width = 14)
    private String nome;

    @Indexed(unique = true)
    @IFieldSearch(textSearchMode = TextSearchMode.contains)
    @IFieldList(width = 24, headerText = "Ufficiale")
    private String nomeCompleto;

    @Indexed()
    @IFieldSearch
    @IFieldList(width = 5)
    private String cap;

    @DBRef()
//    @ARef(linkClazz = RegioneService.class, linkedProperty = "nome")
    @IFieldSearch(placeholder = "Regioni")
    @IFieldList(width = 10)
//    @AFieldForm(linkedProperty = "sigla", clearButtonVisible  = TBool.falso)
    @IFieldForm( clearButtonVisible  = TBool.falso)
    private RegioneEntity regione;

    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
