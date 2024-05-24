package it.algos.geo.regione;

import it.algos.geo.stato.*;
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
@Document(collection = "regione")
@AEntity(usaResetStartup = false)
public class RegioneEntity extends OrdineEntity {


    @Indexed(unique = true)
    @AField(type = TypeField.text, widthList = 6)
    private String sigla;

    @Indexed(unique = true)
    @ASearch(type = TypeSearch.textStartsWith)
    @AField(type = TypeField.wikiAnchor)
    private String nome;

    @DBRef()
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
        return nome;
    }

}// end of Entity class
