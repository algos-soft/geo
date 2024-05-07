package it.algos.geo.regione;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.spring.annotation.*;
import it.algos.geo.list.*;
import it.algos.geo.stato.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.components.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.ui.dialog.*;
import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.*;
import static org.springframework.beans.factory.config.BeanDefinition.*;
import org.springframework.context.annotation.*;

import java.util.*;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class RegioneItaliaList extends GeoList {

    @Autowired
    public StatoService statoModulo;

    private ComboBox comboStato;

    private ComboBox comboType;


    //--non utilizzato. Serve SOLO per evitare un bug di IntelliJIDEA che segnala errore.
    public RegioneItaliaList() {
        super();
    }

    /**
     * @param parentCrudView che crea questa istanza
     */
    public RegioneItaliaList(final RegioneItaliaView parentCrudView) {
        super(parentCrudView);
    }

    @Override
    protected void fixPreferenze() {
        super.fixPreferenze();

        super.usaSelettoreColonne = true;
        super.propertyNames = Arrays.asList("code", "type");
    }

    @Override
    public void syncHeader() {
        String link = "ISO 3166-1";
        String link2 = "ISO 3166-2:xx";

        WAnchor anchor = WAnchor.build(link, textService.setQuadre(link2)).bold();
        BSpan testo = BSpan.text(TEXT_WIKI).bold().verde();
        headerPlaceHolder.add(new Span(testo, new Text(SPAZIO), anchor));

        super.infoScopo = VUOTA;
        super.infoCreazione = TEXT_HARD;
        super.infoReset = TEXT_RESET_DELETE;

        super.fixHeaderPost();
    }

    @PostConstruct
    public void postConstruct() {
        this.fixComboType();
    }

    private void fixComboType() {
        ComboBox comboType = getTopPlaceHolder().getMappaComboBox().get("type");
        List items = new ArrayList<>();
        items.add(TypeRegione.regione);
        items.add(TypeRegione.regioneSpeciale);
        comboType.setItems(items);
        comboType.setWidth("17rem");
    }

}// end of CrudList class
