package it.algos.vbase.backend.modules.geografia.provincia;

import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.backend.modules.geografia.regione.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@AEntity(collectionName = "provincia", keyPropertyName = "sigla", typeList = TypeList.hardCsv, usaIdPrimaMinuscola = false)
public class ProvinciaEntity extends AbstractEntity {

    @AField(type = TypeField.text, widthList = 6)
    private String sigla;

    @AField(type = TypeField.wikiAnchor, widthList = 12, anchorPrefix = "Provincia di ")
    private String nomeBreve;

    @AField(type = TypeField.wikiAnchor, widthList = 24)
    private String nomeCompleto;

    @AField(type = TypeField.linkDBRef, widthList = 10, linkClazz = RegioneEntity.class)
    private RegioneEntity regione;

    @Override
    public String toString() {
        return nomeBreve;
    }

}// end of Entity class
