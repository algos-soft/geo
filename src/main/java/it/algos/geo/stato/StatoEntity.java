package it.algos.geo.stato;

import it.algos.geo.continente.*;
import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import lombok.*;

@Data
@NoArgsConstructor
@AEntity(collectionName = "stato")
public class StatoEntity extends AbstractEntity {

    @AField(type = TypeField.ordine)
    private int ordine;

    @AField(type = TypeField.wikiAnchor)
    private String nome;

    @AField(type = TypeField.wikiAnchor)
    private String capitale;

    @AField(type = TypeField.text, widthList = 6)
    private String alfa3;

    @AField(type = TypeField.text, widthList = 6)
    private String alfa2;

    @AField(type = TypeField.text, headerText = "cod.", widthList = 6)
    private String numerico;

    @AField(type = TypeField.linkWiki)
    private String divisioni;

    //    @DBRef //@todo perché non funziona?
    @AField(type = TypeField.linkDBRef, widthList = 14, linkClazz = ContinenteEntity.class)
    private ContinenteEntity continente;


    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
