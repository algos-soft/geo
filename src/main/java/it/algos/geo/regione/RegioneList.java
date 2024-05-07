package it.algos.geo.regione;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.spring.annotation.*;
import it.algos.geo.list.*;
import it.algos.geo.stato.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.components.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.backend.importexport.*;
import it.algos.vbase.backend.list.*;
import it.algos.vbase.ui.dialog.*;
import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.*;
import static org.springframework.beans.factory.config.BeanDefinition.*;
import org.springframework.context.annotation.*;

import java.util.*;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class RegioneList extends GeoList {

    @Autowired
    public StatoService statoModulo;

    private ComboBox comboStato;

    private ComboBox comboType;


    //--non utilizzato. Serve SOLO per evitare un bug di IntelliJIDEA che segnala errore.
    public RegioneList() {
        super();
    }

    /**
     * @param parentCrudView che crea questa istanza
     */
    public RegioneList(final RegioneView parentCrudView) {
        super(parentCrudView);
    }


    @Override
    public void syncHeader() {
        String link = "ISO 3166-1";
        String link2 = "ISO 3166-2:xx";

        WAnchor anchor = WAnchor.build(  link, textService.setQuadre( link2)).bold();
        BSpan testo = BSpan.text( TEXT_WIKI).bold().verde();
        headerPlaceHolder.add(new Span(testo, new Text(SPAZIO), anchor));

        super.infoScopo = VUOTA;
        super.infoCreazione = TEXT_HARD;
        super.infoReset = TEXT_RESET_DELETE;

        super.fixHeaderPost();
    }

//    /**
//     * Aggiunge componenti al Top della Lista <br>
//     * Può essere sovrascritto, invocando PRIMA il metodo della superclasse se si vogliono aggiungere componenti IN CODA <br>
//     * Può essere sovrascritto, SENZA invocare il metodo della superclasse se si vogliono aggiungere componenti in ordine diverso <br>
//     */
//    @Override
//    protected void fixTop() {
//        super.fixTop();
//
//        comboStato = new ComboBox<>();
//        comboStato.setPlaceholder("Stati...");
//        comboStato.setClearButtonVisible(true);
//        comboStato.setWidth("14rem");
//        comboStato.setItems(statoModulo.findAllEuropa());
//        comboStato.addValueChangeListener(event -> sync());
//        topPlaceHolder.add(comboStato);
//
//        comboType = new ComboBox<>();
//        comboType.setPlaceholder("Type...");
//        comboType.setClearButtonVisible(true);
//        comboType.setWidth("14rem");
//        comboType.setItems(TypeRegione.values());
//        comboType.addValueChangeListener(event -> sync());
//        topPlaceHolder.add(comboType);
//    }
//
//    @Override
//    protected void syncFiltri() {
//        if (comboStato != null) {
//            if (comboStato.getValue() != null) {
//                if (comboStato.getValue() instanceof StatoEntity stato) {
//                    filtri.uguale("stato", stato);
//                }
//            }
//            else {
//                filtri.remove("stato");
//            }
//        }
//        if (comboType != null) {
//            if (comboType.getValue() != null) {
//                if (comboType.getValue() instanceof TypeRegione type) {
//                    filtri.uguale("type", type);
//                }
//            }
//            else {
//                filtri.remove("type");
//            }
//        }
//    }



    public ExcelExporter creaExcelExporter() {
        String[] properties = {"sigla", "nome", "stato", "type"};
        ExcelExporter exporter = new ExcelExporter(RegioneEntity.class, filtri, List.of(properties), mongoService);

        exporter.setTitle("Lista delle regioni");
        exporter.setColumnWidth("sigla", 8);
        exporter.setColumnWidth("nome", 20);
        exporter.setColumnWidth("stato", 20);
        exporter.setColumnWidth("type", 20);

        return exporter;
    }

}// end of CrudList class
