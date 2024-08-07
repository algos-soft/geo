package it.algos.geo.comune;

import it.algos.geo.provincia.ProvinciaEntity;
import it.algos.geo.regione.RegioneEntity;
import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.OrdineEntity;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "comune")
@AReset
@AEntity()
public class ComuneEntity extends OrdineEntity {


    @Indexed(unique = true)
    @ASearch
    @AFieldList(width = 18)
//    @AField(type = TypeField.text)
    private String nome;

    @Indexed(unique = true)
    @AFieldList(width = 18, headerText = "Wiki")
    @AFieldForm(label = "WikiPagina")
//    @AField(type = TypeField.wikiAnchor)
    private String pagina;

    @DBRef()
    @ASearch(placeholder = "Province")
    @AFieldList(width = 14)
//    @AField(type = TypeField.linkDBRef, linkClazz = ProvinciaEntity.class)
    private ProvinciaEntity provincia;

    @Indexed()
    @ASearch
    @AFieldList(width = 6)
//    @AField(type = TypeField.text)
    private String cap;

    @DBRef()
    @ASearch(placeholder = "Regioni")
    @AFieldList(width = 10)
//    @AField(type = TypeField.linkDBRef, linkClazz = RegioneEntity.class)
    private RegioneEntity regione;


    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
