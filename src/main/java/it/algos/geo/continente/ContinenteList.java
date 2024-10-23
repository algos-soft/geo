package it.algos.geo.continente;

import it.algos.geo.list.GeoList;
import it.algos.vbase.annotation.AViewList;
import it.algos.vbase.constant.Bottone;

import static it.algos.vbase.boot.BaseCost.*;

@AViewList(bottoni = {Bottone.RESET_DELETE, Bottone.SHOW})
public class ContinenteList extends GeoList {


    /**
     * @param parentCrudView che crea questa istanza
     */
    public ContinenteList(final ContinenteView parentCrudView) {
        super(parentCrudView);
    }

    protected void fixPreferenze() {
        super.readOnly = true;
    }


    @Override
    public void fixHeader() {
        String enumeration = "ContinenteEnum";
        String service = "Continente";

        super.infoScopo = String.format(TEXT_TAVOLA + SPAZIO + TEXT_ENUM, enumeration, service); ;
        super.infoCreazione = TEXT_HARD;
        super.infoReset = TEXT_RESET_DELETE;

        super.fixHeader();
        super.fixAdmin();
    }


}// end of AList class
