package it.algos.geo.stato;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.spring.annotation.*;
import it.algos.geo.continente.*;
import it.algos.geo.list.*;
import it.algos.vbase.backend.boot.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.components.*;
import it.algos.vbase.backend.importexport.*;
import it.algos.vbase.ui.dialog.*;
import org.springframework.beans.factory.annotation.*;
import static org.springframework.beans.factory.config.BeanDefinition.*;
import org.springframework.context.annotation.*;

import java.util.*;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class StatoList extends GeoList {

    static final String FIELD_CAPITALE = "capitale";

    static final String FIELD_ALFA_3 = "alfa3";

    static final String FIELD_ALFA_2 = "alfa2";

    static final String FIELD_NUMERICO = "numerico";

    static final String FIELD_CONTINENTE = "continente";

    //--searchField locale per selezionare la property
    private TextField searchCapitale;

    //--searchField locale per selezionare la property
    private TextField searchAlfa3;

    //--searchField locale per selezionare la property
    private TextField searchAlfa2;

    //--searchField locale per selezionare la property
    private TextField searchNumerico;

    //--comboBox locale per selezionare la property
    ComboBox<ContinenteEntity> comboContinente;

    @Autowired
    public ContinenteService continenteModulo;


    //--non utilizzato. Serve SOLO per evitare un bug di IntelliJIDEA che segnala errore.
    public StatoList() {
        super();
    }

    /**
     * @param parentCrudView che crea questa istanza
     */
    public StatoList(final StatoView parentCrudView) {
        super(parentCrudView);
    }

    protected void fixPreferenze() {
        super.fixPreferenze();

        super.usaBottoneEdit = true;
        super.usaBottoneShow = false;
    }

    @Override
    public void syncHeader() {
        Anchor anchor1;
        Anchor anchor2;
        Anchor anchor3;
        String alfa3 = "ISO 3166-1 alpha-3";
        String capitali = "Capitali degli Stati del mondo";
        String alfa2 = "ISO 3166-1";

        anchor1 = WAnchor.build(alfa3, textService.setQuadre(alfa3)).bold();
        anchor2 = WAnchor.build(capitali, textService.setQuadre(capitali)).bold();
        anchor3 = WAnchor.build(alfa2, textService.setQuadre(alfa2)).bold();

        BSpan testo = BSpan.text(TEXT_WIKI).bold().verde();
        headerPlaceHolder.add(new Span(testo, new Text(SPAZIO), anchor1, new Text(SPAZIO), anchor2, new Text(SPAZIO), anchor3));

        super.infoScopo = VUOTA;
        super.infoCreazione = TEXT_HARD;
        super.infoReset = TEXT_RESET_DELETE;

        super.fixHeaderPost();
        super.fixAdmin();
    }

    /**
     * Aggiunge componenti al Top della Lista <br>
     */
    @Override
    protected void addTop() {
        //--creazione 'ad hoc' di un textSearch (semistandard) per selezionare l'inizio del testo della property -> descrizione
//        searchCapitale = super.creaFiltroText(FIELD_CAPITALE);

        //--creazione 'ad hoc' di un textSearch (semistandard) per selezionare l'inizio del testo della property -> descrizione
//        searchAlfa3 = super.creaFiltroText(FIELD_ALFA_3);

        //--creazione 'ad hoc' di un textSearch (semistandard) per selezionare l'inizio del testo della property -> descrizione
//        searchAlfa2 = super.creaFiltroText(FIELD_ALFA_2);

        //--creazione 'ad hoc' di un textSearch (semistandard) per selezionare l'inizio del testo della property -> descrizione
//        searchNumerico = super.creaFiltroText(FIELD_NUMERICO);

        //--creazione 'ad hoc' di un comboBox (semistandard) per selezionare la property nome
//        comboContinente = super.creaFiltroCombo(FIELD_CONTINENTE, continenteModulo.findAll());
    }

    @Override
    protected void syncFiltri() {
        //--filtraggio del database in funzione del valore della property (inizio del testo)
//        super.filtroInizioText(searchCapitale, FIELD_CAPITALE);

        //--filtraggio del database in funzione del valore della property (inizio del testo)
//        super.filtroInizioText(searchAlfa3, FIELD_ALFA_3);

        //--filtraggio del database in funzione del valore della property (inizio del testo)
//        super.filtroInizioText(searchAlfa2, FIELD_ALFA_2);

        //--filtraggio del database in funzione del valore della property (inizio del testo)
//        super.filtroInizioText(searchNumerico, FIELD_NUMERICO);

        //--filtraggio del database in funzione del valore della property
//        super.filtroComboClazz(comboContinente, FIELD_CONTINENTE);
    }


    public ExcelExporter creaExcelExporter() {
        String[] properties = {"ordine", "nome", "capitale", "alfa3", "alfa2"};
        ExcelExporter exporter = new ExcelExporter(StatoEntity.class, filtri, List.of(properties), mongoService);

        exporter.setTitle("Lista degli stati");
        exporter.setColumnWidth("ordine", 8);
        exporter.setColumnWidth("nome", 30);
        exporter.setColumnWidth("capitale", 20);
        exporter.setColumnWidth("alfa3", 8);
        exporter.setColumnWidth("alfa2", 8);

        return exporter;
    }

}// end of CrudList class
