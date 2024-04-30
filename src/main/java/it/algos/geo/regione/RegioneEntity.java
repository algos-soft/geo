package it.algos.geo.regione;

import it.algos.geo.stato.*;
import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@AEntity(collectionName = "regione", keyPropertyName = "sigla", searchPropertyName = "nome", typeList = TypeList.hardWiki, usaIdPrimaMinuscola = false)
public class RegioneEntity extends AbstractEntity {

    @AField(type = TypeField.ordine, widthList = 5)
    private int ordine;

    @AField(type = TypeField.text)
    private String sigla;

    @AField(type = TypeField.text, widthList = 14)
    private String nome;

    //    @DBRef //@todo perché non funziona?
    @AField(type = TypeField.linkDBRef, widthList = 14, linkClazz = StatoEntity.class)
    private StatoEntity stato;

    @AField(type = TypeField.linkWiki)
    private String pagina;

    @AField(type = TypeField.enumType, enumClazz = TypeRegione.class, widthList = 20)
    private TypeRegione type;

    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
