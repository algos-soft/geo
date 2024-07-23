package it.algos.geo.comune;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import it.algos.geo.list.GeoList;
import it.algos.vbase.backend.annotation.AList;
import it.algos.vbase.backend.constant.Bottone;
import it.algos.vbase.backend.enumeration.FontWeight;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;

import static it.algos.vbase.backend.boot.BaseCost.*;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Scope(value = SCOPE_PROTOTYPE)
@AList(bottoni = {Bottone.DOWNLOAD})
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

//        super.usaBottoneDownload = true;
//        super.usaBottoneEdit = true;
//        super.usaBottoneShow = false;
//        super.usaBottoneExport = true;
    }

    @Override
    public void fixHeader() {
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
//        Button bottone = getListToolbar().getButton(ListButtons.download);
//        bottone.addThemeVariants(ButtonVariant.LUMO_ERROR);
//        bottone.setTooltipText(TEXT_DOWNLOAD);
//        bottone.setIcon(new Icon(VaadinIcon.PUZZLE_PIECE));
//        bottone.addClickListener(event -> ((ComuneService)moduloService).elabora());

//        List items = new ArrayList<>();
//        items.add(TypePagamento.cont);
//        items.add(TypePagamento.cc);
//        items.add(TypePagamento.paypal);
//        comboType.setItems(items);
//        comboType.setWidth("10rem");
    }


}// end of CrudList class
