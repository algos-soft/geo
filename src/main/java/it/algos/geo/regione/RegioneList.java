package it.algos.geo.regione;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.geo.list.GeoList;
import it.algos.geo.stato.StatoService;
import it.algos.vbase.annotation.AViewList;
import it.algos.vbase.components.WAnchor;
import it.algos.vbase.ui.dialog.BSpan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import static it.algos.vbase.boot.BaseCost.*;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
@AViewList()
public class RegioneList extends GeoList {

    @Autowired
    public StatoService statoModulo;

    private ComboBox comboStato;

    private ComboBox comboType;


    /**
     * @param parentCrudView che crea questa istanza
     */
    public RegioneList(final RegioneView parentCrudView) {
        super(parentCrudView);
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


//    public ExcelExporter creaExcelExporter() {
//        String[] properties = {"sigla", "nome", "stato", "type"};
//        ExcelExporter exporter = new ExcelExporter(RegioneEntity.class, filtri, List.of(properties), mongoService);
//
////        exporter.setTitle("Lista delle regioni");
//        exporter.setColumnWidth("sigla", 8);
//        exporter.setColumnWidth("nome", 20);
//        exporter.setColumnWidth("stato", 20);
//        exporter.setColumnWidth("type", 20);
//
//        return exporter;
//    }

}// end of AList class
