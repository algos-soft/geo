package it.algos.geo.provincia;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.geo.list.GeoList;
import it.algos.vbase.backend.annotation.AList;
import it.algos.vbase.backend.components.BAnchor;
import it.algos.vbase.ui.dialog.BSpan;
import org.springframework.context.annotation.Scope;

import static it.algos.vbase.backend.boot.BaseCost.*;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
@AList()
public class ProvinciaList extends GeoList {


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

    protected void fixPreferenze() {
        super.fixPreferenze();
    }

    @Override
    public void fixHeader() {
        String link = "province";
        BAnchor anchor = BAnchor.build(LINK_SERVER_ALGOS + link, textService.setQuadre("algos -> " + link));
        BSpan testo = BSpan.text(TEXT_BASE + SPAZIO + TEXT_CSV).bold().verde();
        headerPlaceHolder.add(new Span(testo, new Text(SPAZIO), anchor));

        super.infoScopo = VUOTA;
        super.infoCreazione = TEXT_HARD;
        super.infoReset = TEXT_RESET_DELETE;

        super.fixHeaderPost();
    }


}// end of CrudList class
