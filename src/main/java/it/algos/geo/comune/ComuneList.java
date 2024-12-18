package it.algos.geo.comune;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import it.algos.geo.list.GeoList;
import it.algos.vbase.annotation.IList;
import it.algos.vbase.constant.Bottone;
import it.algos.vbase.enumeration.FontWeight;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;

import static it.algos.vbase.boot.BaseCost.*;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Scope(value = SCOPE_PROTOTYPE)
@IList(bottoni = {Bottone.DOWNLOAD})
public class ComuneList extends GeoList {


    /**
     * @param parentCrudView che crea questa istanza
     */
    public ComuneList(final ComuneView parentCrudView) {
        super(parentCrudView);
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

        headerPlaceHolder.add(new Span(anchor));

        super.fixHeader();
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


}// end of AList class
