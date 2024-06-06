package it.algos.geo.comune;

import com.vaadin.flow.component.button.*;
import com.vaadin.flow.component.grid.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.*;
import com.vaadin.flow.spring.annotation.*;
import it.algos.geo.list.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.enumeration.*;
import jakarta.annotation.*;
import static org.springframework.beans.factory.config.BeanDefinition.*;
import org.springframework.context.annotation.*;

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

        super.gridSelectionMode = Grid.SelectionMode.MULTI;
        super.usaBottoneDownload = true;
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

        headerPlaceHolder.add(new Span( anchor));

        super.fixHeaderPost();
    }


    @PostConstruct
    private void regolazioniDellaClasseSpecificaDopoAverRegolatoLaSuperclasse() {
        this.modificaBottone();
    }



    private void modificaBottone() {
        Button bottone = getButtonBar().getButtonDownload();
        bottone.addThemeVariants(ButtonVariant.LUMO_ERROR);
        bottone.setTooltipText(TEXT_DOWNLOAD);
        bottone.setIcon(new Icon(VaadinIcon.PUZZLE_PIECE));
        bottone.addClickListener(event -> ((ComuneService)moduloService).elabora());

//        List items = new ArrayList<>();
//        items.add(TypePagamento.cont);
//        items.add(TypePagamento.cc);
//        items.add(TypePagamento.paypal);
//        comboType.setItems(items);
//        comboType.setWidth("10rem");
    }


}// end of CrudList class
