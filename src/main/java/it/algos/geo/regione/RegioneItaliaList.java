package it.algos.geo.regione;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.geo.list.GeoList;
import it.algos.geo.stato.StatoService;
import it.algos.vbase.annotation.IList;
import it.algos.vbase.components.WAnchor;
import it.algos.vbase.ui.dialog.BSpan;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import static it.algos.vbase.boot.BaseCost.*;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
@IList(columns = {"nome", "type"})
public class RegioneItaliaList extends GeoList {

    @Autowired
    public StatoService statoModulo;

    private ComboBox comboStato;

    private ComboBox comboType;


    /**
     * @param parentCrudView che crea questa istanza
     */
    public RegioneItaliaList(final RegioneItaliaView parentCrudView) {
        super(parentCrudView);
    }

    @Override
    protected void fixPreferenze() {
        super.fixPreferenze();
    }

    @Override
    public void fixHeader() {
        String link = "ISO 3166-1";
        String link2 = "ISO 3166-2:xx";

        WAnchor anchor = WAnchor.build(link, textService.setQuadre(link2)).bold();
        BSpan testo = BSpan.text(TEXT_WIKI).bold().verde();
        headerPlaceHolder.add(new Span(testo, new Text(SPAZIO), anchor));

        super.infoScopo = VUOTA;
        super.infoCreazione = TEXT_HARD;
        super.infoReset = TEXT_RESET_DELETE;

        super.fixHeader();
    }

    @PostConstruct
    public void postConstruct() {
        this.fixComboType();
    }

    private void fixComboType() {
//        ComboBox comboType = getListToolbar().getMappaComboBox().get("type");
//        List items = new ArrayList<>();
//        items.add(TypeRegione.regione);
//        items.add(TypeRegione.regioneSpeciale);
//        if (comboType != null) {
//            comboType.setItems(items);
//            comboType.setWidth("17rem");
//        }

    }

}// end of AList class
