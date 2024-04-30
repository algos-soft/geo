package it.algos.vbase.backend.modules.geografia.provincia;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.spring.annotation.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.components.*;
import it.algos.vbase.backend.list.*;
import it.algos.vbase.backend.modules.geografia.regione.*;
import it.algos.vbase.ui.dialog.*;
import static org.springframework.beans.factory.config.BeanDefinition.*;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.*;

import javax.inject.*;
import java.util.*;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class ProvinciaList extends CrudList {

    @Inject
    RegioneService regioneModulo;

    TextField searchField;

    private TextField searchNomeBreve;

    private ComboBox comboRegione;


    //--non utilizzato. Serve SOLO per evitare un bug di IntelliJIDEA che segnala errore.
    public ProvinciaList() {
        super();
    }

    /**
     * @param parentCrudView che crea questa istanza
     */
    public ProvinciaList(final ProvinciaView parentCrudView) {
        super(parentCrudView);
    }


    @Override
    public void syncHeader() {
        String link = "province";
        BAnchor anchor = BAnchor.build(LINK_SERVER_ALGOS + link, textService.setQuadre("algos -> " + link));
        BSpan testo = BSpan.text(TEXT_BASE + SPAZIO + TEXT_CSV).bold().verde();
        headerPlaceHolder.add(new Span(testo, new Text(SPAZIO), anchor));

        super.infoScopo = VUOTA;
        super.infoCreazione = TEXT_HARD;
        super.infoReset = TEXT_RESET_DELETE;

        super.fixHeaderPost();
    }


    /**
     * Aggiunge componenti al Top della Lista <br>
     * Può essere sovrascritto, invocando PRIMA il metodo della superclasse se si vogliono aggiungere componenti IN CODA <br>
     * Può essere sovrascritto, SENZA invocare il metodo della superclasse se si vogliono aggiungere componenti in ordine diverso <br>
     */
    @Override
    protected void fixTop() {
        super.fixTop();
        List itemsRegione = regioneModulo.findAllItalia();

        searchNomeBreve = new TextField();
        searchNomeBreve.setPlaceholder(TAG_ALTRE_BY + "nome breve");
        searchNomeBreve.getElement().setProperty("title", "Search: ricerca testuale da inizio del campo " + searchNomeBreve);
        searchNomeBreve.setClearButtonVisible(true);
        searchNomeBreve.addValueChangeListener(event -> sync());
        topPlaceHolder.add(searchNomeBreve);

        comboRegione = new ComboBox<>();
        comboRegione.setPlaceholder("Regioni...");
        comboRegione.setClearButtonVisible(true);
        comboRegione.setWidth("14rem");
        if (itemsRegione != null && itemsRegione.size() > 0) {
            comboRegione.setItems(itemsRegione);
        }
        comboRegione.addValueChangeListener(event -> sync());
        topPlaceHolder.add(comboRegione);
    }


    @Override
    protected void syncFiltri() {
        String searchProperty = "nomeBreve";
        String regioneProperty = "regione";
        String value = VUOTA;
        if (searchNomeBreve != null) {
            value = searchNomeBreve.getValue();
        }

        if (textService.isValid(value)) {
            filtri.inizio(searchProperty, value);
            filtri.sort(Sort.by(Sort.Direction.ASC, value));
        }
        else {
            filtri.remove(searchProperty);
            filtri.sort(basicSort);
        }

        if (comboRegione != null) {
            if (comboRegione.getValue() != null) {
                if (comboRegione.getValue() instanceof RegioneEntity regione) {
                    filtri.uguale(regioneProperty, regione);
                }
            }
            else {
                filtri.remove(regioneProperty);
            }
        }
    }

}// end of CrudList class
