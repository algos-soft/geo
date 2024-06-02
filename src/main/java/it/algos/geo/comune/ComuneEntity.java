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
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "comune")
@AReset
@AEntity()
public class ComuneEntity extends OrdineEntity {


    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textStartsWith)
    @AFieldList(width = 18)
    @AField(type = TypeField.text)
    private String nome;

    @Indexed(unique = true)
    @AFieldList(width = 18,headerText = "Wiki")
    @AField(type = TypeField.wikiAnchor, caption = "WikiPagina")
    private String pagina;

    @DBRef()
    @ASearch(type = TypeSearch.comboClazz)
    @AFieldList(width = 14)
    @AField(type = TypeField.linkDBRef, linkClazz = ProvinciaEntity.class)
    private ProvinciaEntity provincia;

    @Indexed()
    @ASearch(type = TypeSearch.textStartsWith)
    @AFieldList(width = 6)
    @AField(type = TypeField.text)
    private String cap;

    @DBRef()
    @ASearch(type = TypeSearch.comboClazz)
    @AFieldList(width = 10)
    @AField(type = TypeField.linkDBRef, linkClazz = RegioneEntity.class)
    private RegioneEntity regione;


    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
