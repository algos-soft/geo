package it.algos.geo.comune;

import it.algos.geo.provincia.ProvinciaEntity;
import it.algos.geo.regione.RegioneEntity;
import it.algos.vbase.annotation.*;
import it.algos.vbase.entity.OrdineEntity;
import it.algos.vbase.enumeration.TBool;
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
    private String nome;

    @Indexed(unique = true)
    @AAnchor
    @AFieldList(width = 18, headerText = "Wiki")
    @AFieldForm(label = "WikiPagina")
    private String pagina;

    @DBRef()
    @AAnchor
//    @ARef(linkClazz = ProvinciaService.class, linkedProperty = "nome")
    @ASearch(placeholder = "Province")
    @AFieldList(width = 14)
    @AFieldForm(clearButtonVisible = TBool.falso)
    private ProvinciaEntity provincia;

    @Indexed()
    @ASearch
    @AFieldList(width = 6)
    private String cap;

    @DBRef()
    @AAnchor
//    @ARef(linkClazz = RegioneService.class, linkedProperty = "nome")
    @ASearch(placeholder = "Regioni")
    @AFieldList(width = 10)
    @AFieldForm(clearButtonVisible = TBool.falso)
    private RegioneEntity regione;


    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
