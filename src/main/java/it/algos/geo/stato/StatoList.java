package it.algos.geo.stato;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.spring.annotation.*;
import it.algos.geo.continente.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.components.*;
import it.algos.vbase.backend.importexport.*;
import it.algos.vbase.backend.list.*;
import it.algos.vbase.ui.dialog.*;
import org.springframework.beans.factory.annotation.*;
import static org.springframework.beans.factory.config.BeanDefinition.*;
import org.springframework.context.annotation.*;

import java.util.*;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class StatoList extends CrudList {

    @Autowired
    public ContinenteService continenteModulo;

    private ComboBox comboContinente;



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


    @Override
    public void syncHeader() {
        Anchor anchor1;
        Anchor anchor2;
        Anchor anchor3;
        String link;
        String caption;
        String alfa3 = "ISO 3166-1 alpha-3";
        String capitali = "Capitali degli Stati del mondo";
        String alfa2 = "ISO 3166-1";

        link = String.format("%s%s", TAG_WIKI, alfa3);
        anchor1 = WAnchor.build(link, textService.setQuadre(alfa3)).bold();

//        caption = String.format("%s%s%s", QUADRA_INI, alfa3, QUADRA_END);
//        anchor1 = new Anchor(link, caption);
//        anchor1.getElement().getStyle().set(FontWeight.HTML, FontWeight.bold.getTag());

        link = String.format("%s%s", TAG_WIKI, capitali);
        anchor2 = WAnchor.build(link, textService.setQuadre(capitali)).bold();
//        caption = String.format("%s%s%s", QUADRA_INI, capitali, QUADRA_END);
//        anchor2 = new Anchor(link, caption);
//        anchor2.getElement().getStyle().set(FontWeight.HTML, FontWeight.bold.getTag());

        link = String.format("%s%s", TAG_WIKI, alfa2);
        anchor3 = WAnchor.build(link, textService.setQuadre(alfa2)).bold();
//        caption = String.format("%s%s%s", QUADRA_INI, alfa2, QUADRA_END);
//        anchor3 = new Anchor(link, caption);
//        anchor3.getElement().getStyle().set(FontWeight.HTML, FontWeight.bold.getTag());
        BSpan testo = BSpan.text( TEXT_WIKI).bold().verde();
        headerPlaceHolder.add(new Span(testo, new Text(SPAZIO), anchor1,new Text(SPAZIO), anchor2,new Text(SPAZIO), anchor3));

//        Span testo = new Span(typeList.getInfoScopo());
//        testo.getStyle().set(FontWeight.HTML, FontWeight.bold.getTag());
//        testo.getStyle().set(TAG_HTML_COLOR, TypeColor.verde.getTag());

        super.infoScopo = VUOTA;
        super.infoCreazione = TEXT_HARD;
        super.infoReset = TEXT_RESET_DELETE;


//        headerPlaceHolder.add(new Span(testo, anchor1, new Text(VIRGOLA_SPAZIO), anchor2, new Text(VIRGOLA_SPAZIO), anchor3));
//        headerPlaceHolder.add(new Span(testo, new Text(SPAZIO), anchor));

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

        comboContinente = new ComboBox<>();
        comboContinente.setPlaceholder("Continenti...");
        comboContinente.setClearButtonVisible(true);
        comboContinente.setWidth("14rem");
        comboContinente.setItems(continenteModulo.findAll());
        comboContinente.addValueChangeListener(event -> sync());
        topPlaceHolder.add(comboContinente);
    }

    @Override
    protected void syncFiltri() {
        if (comboContinente != null) {
            if (comboContinente.getValue() != null) {
                if (comboContinente.getValue() instanceof ContinenteEntity continente) {
                    filtri.uguale("continente", continente);
                }
            }
            else {
                filtri.remove("continente");
            }
        }
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
