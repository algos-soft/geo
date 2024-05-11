package it.algos.geo.comune;

import com.vaadin.flow.component.combobox.*;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.spring.annotation.*;
import it.algos.geo.list.*;
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
public class ComuneList extends GeoList {



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

    protected void fixPreferenze() {
        super.fixPreferenze();

        super.gridSelection = Grid.SelectionMode.MULTI;
        super.usaBottoneEdit = true;
        super.usaBottoneShow = false;
        super.usaBottoneExport = true;
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

//        Span testo = new Span(typeList.getInfoScopo());
//        testo.getStyle().set(FontWeight.HTML, FontWeight.bold.getTag());
//        testo.getStyle().set(TAG_HTML_COLOR, TypeColor.verde.getTag());
//        headerPlaceHolder.add(new Span(testo, anchor));
        headerPlaceHolder.add(new Span( anchor));

        super.fixHeaderPost();
    }


}// end of CrudList class
