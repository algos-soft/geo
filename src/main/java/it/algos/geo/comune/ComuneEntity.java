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
@IReset
@IEntity()
public class ComuneEntity extends OrdineEntity {


    @Indexed(unique = true)
    @ISearch
    @IFieldList(width = 18)
    private String nome;

    @Indexed(unique = true)
    @IAnchor
    @IFieldList(width = 18, headerText = "Wiki")
    @IFieldForm(label = "WikiPagina")
    private String pagina;

    @DBRef()
    @IAnchor
//    @ARef(linkClazz = ProvinciaService.class, linkedProperty = "nome")
    @ISearch(placeholder = "Province")
    @IFieldList(width = 14)
    @IFieldForm(clearButtonVisible = TBool.falso)
    private ProvinciaEntity provincia;

    @Indexed()
    @ISearch
    @IFieldList(width = 6)
    private String cap;

    @DBRef()
    @IAnchor
//    @ARef(linkClazz = RegioneService.class, linkedProperty = "nome")
    @ISearch(placeholder = "Regioni")
    @IFieldList(width = 10)
    @IFieldForm(clearButtonVisible = TBool.falso)
    private RegioneEntity regione;


    @Override
    public String toString() {
        return nome;
    }

}// end of Entity class
