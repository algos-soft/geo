package it.algos.geo.stato;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.spring.annotation.*;
import it.algos.geo.list.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.components.*;
import it.algos.vbase.backend.importexport.*;
import it.algos.vbase.ui.dialog.*;
import static org.springframework.beans.factory.config.BeanDefinition.*;
import org.springframework.context.annotation.*;

import java.util.*;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
public class StatoList extends GeoList {


    //--non utilizzato. Serve SOLO per evitare un bug di IntelliJIDEA che segnala errore.
    public StatoList() {
        super();
    }

    /*
     * @param parentCrudView che crea questa istanza
     */
    public StatoList(final StatoView parentCrudView) {
        super(parentCrudView);
    }

    protected void fixPreferenze() {
        super.fixPreferenze();

        super.gridSelectionMode = Grid.SelectionMode.MULTI;
        super.usaBottoneEdit = true;
        super.usaBottoneShow = false;
        super.usaBottoneExport = true;
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
