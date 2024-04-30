package it.algos.geo.comune;

import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.spring.annotation.*;
import it.algos.geo.provincia.*;
import it.algos.geo.regione.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.backend.list.*;
import static org.springframework.beans.factory.config.BeanDefinition.*;
import org.springframework.context.annotation.Scope;

import javax.inject.*;
import java.util.*;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class ComuneList extends CrudList {

    @Inject
    ProvinciaService provinciaModulo;

    @Inject
    RegioneService regioneModulo;

    private ComboBox comboProvincia;

    private ComboBox comboRegione;



    //--non utilizzato. Serve SOLO per evitare un bug di IntelliJIDEA che segnala errore.
    public ComuneList() {
        super();
    }

    /**
     * @param parentCrudView che crea questa istanza
     */
    public ComuneList(final ComuneView parentCrudView) {
        super(parentCrudView);
    }


    @Override
    public void syncHeader() {
        Anchor anchor;
        String link;
        String caption;
        String alfa = "Comuni d'Italia (A)";

        link = String.format("%s%s", TAG_WIKI, alfa);
        caption = String.format("%s%s%s%s", QUADRA_INI, alfa, QUADRA_END, " B, C, ...");
        anchor = new Anchor(link, caption);
        anchor.getElement().getStyle().set(FontWeight.HTML, FontWeight.bold.getTag());

        Span testo = new Span(typeList.getInfoScopo());
        testo.getStyle().set(FontWeight.HTML, FontWeight.bold.getTag());
        testo.getStyle().set(TAG_HTML_COLOR, TypeColor.verde.getTag());
        headerPlaceHolder.add(new Span(testo, anchor));

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
        List itemsProvincia = provinciaModulo.findAll();
        List itemsRegione = regioneModulo.findAllItalia();

        comboProvincia = new ComboBox<>();
        comboProvincia.setPlaceholder("Province...");
        comboProvincia.setClearButtonVisible(true);
        comboProvincia.setWidth("14rem");
        if (itemsProvincia != null && itemsProvincia.size() > 0) {
            comboProvincia.setItems(itemsProvincia);
        }
        comboProvincia.addValueChangeListener(event -> sync());
        topPlaceHolder.add(comboProvincia);

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
        String provinciaProperty = "provincia";
        String regioneProperty = "regione";

        if (comboProvincia != null) {
            if (comboProvincia.getValue() != null) {
                if (comboProvincia.getValue() instanceof ProvinciaEntity provincia) {
                    filtri.uguale(provinciaProperty, provincia);
                }
            }
            else {
                filtri.remove(provinciaProperty);
            }
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

    /**
     * Regola le property visibili in una lista CrudList <br>
     * Di default prende tutti i fields della AbstractEntity specifica <br>
     * Può essere sovrascritto SENZA richiamare il metodo della superclasse <br>
     * <p>
     * Invoca il metodo del Service perché, di norma, i fields sono gli stessi tra List e Form <br>
     */
    @Override
    public List<String> getPropertyNames() {
        return Arrays.asList("nome", "provincia", "regione");
    }


}// end of CrudList class
