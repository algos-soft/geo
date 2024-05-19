package it.algos.geo.stato;

import it.algos.geo.continente.*;
import it.algos.geo.enumeration.*;
import it.algos.geo.logic.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.backend.wrapper.*;
import org.apache.commons.lang3.*;
import org.bson.types.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * Project base24
 * Created by Algos
 * User: gac
 * Date: mer, 25-ott-2023
 * Time: 11:42
 */
@Service
public class StatoService extends GeoModuloService {

    @Value("${algos.project.crea.directory.geo:false}")
    private String creaDirectoryGeoTxt;

    @Autowired
    public ContinenteService continenteModulo;

    /**
     * Costruttore invocato dalla sottoclasse concreta obbligatoriamente con due parametri <br>
     * Regola la entityClazz associata a questo Modulo <br>
     * Regola la viewClazz @Route associata a questo Modulo <br>
     */
    public StatoService() {
        super(StatoEntity.class, StatoView.class);
    }

    //    public StatoEntity creaIfNotExists(String nome, String capitale, String alfa3, String alfa2) {
    //        if (existByKey(nome)) {
    //            return null;
    //        }
    //        else {
    //            return (StatoEntity) insert(newEntity(nome, capitale, alfa3, alfa2));
    //        }
    //    }

    /**
     * Creazione in memoria di una nuova entity che NON viene salvata <br>
     *
     * @return la nuova entity appena creata (con keyID ma non salvata)
     */
    @Override
    public StatoEntity newEntity() {
        return newEntity(0, VUOTA, VUOTA, VUOTA, VUOTA, VUOTA, null, VUOTA);
    }

    public StatoEntity newEntity(String code, String capitale, String alfa3, String alfa2) {
        return newEntity(0, code, capitale, alfa3, alfa2, VUOTA, null, VUOTA);
    }

    public StatoEntity newEntity(int ordine, String code, String alfa3) {
        return newEntity(ordine, code, VUOTA, alfa3, VUOTA, VUOTA, null, VUOTA);
    }

    public StatoEntity newEntity(int ordine, String code, String alfa3, String linkDivisioni) {
        return newEntity(ordine, code, VUOTA, alfa3, VUOTA, VUOTA, null, linkDivisioni);
    }

    /**
     * Creazione in memoria di una nuova entity che NON viene salvata <br>
     *
     * @param code     (obbligatorio)
     * @param capitale (facoltativo)
     *
     * @return la nuova entity appena creata (con keyID ma non salvata)
     */
    public StatoEntity newEntity(
            int ordine,
            String nome,
            String capitale,
            String alfa3,
            String alfa2,
            String numerico,
            ContinenteEntity continente,
            String divisioni) {

        StatoEntity newEntityBean = StatoEntity.builder()
                .nome(textService.isValid(nome) ? nome : null)
                .capitale(textService.isValid(capitale) ? capitale : null)
                .alfa3(textService.isValid(alfa3) ? alfa3 : null)
                .alfa2(textService.isValid(alfa2) ? alfa2 : null)
                .numerico(textService.isValid(numerico) ? numerico : null)
                .continente(continente)
                .divisioni(textService.isValid(divisioni) ? divisioni : null)
                .unioneEuropea(false)
                .build();

        newEntityBean.setOrdine(ordine == 0 ? nextOrdine() : ordine);
        return newEntityBean;
    }

    @Override
    public ObjectId getObjectId(AbstractEntity newEntityBean) {
        return new ObjectId(textService.fixSize(((StatoEntity) newEntityBean).getAlfa3(), ID_LENGTH).getBytes());
    }

    @Override
    public StatoEntity findById(final String idStringValue) {
        return (StatoEntity) super.findById(idStringValue);
    }

    @Override
    public List<StatoEntity> findAll() {
        return super.findAll();
    }


    public List<StatoEntity> findAllEuropa() {
        return findAll()
                .stream()
                .filter(stato -> stato.getContinente() != null ? stato.getContinente().getNome().equals(ContinenteEnum.europa.getTag()) : false)
                .toList();
    }



    public RisultatoReset reset() {
        if (!Boolean.parseBoolean(creaDirectoryGeoTxt)) {
            return RisultatoReset.nonCostruito;
        }
        if (continenteModulo.count() < 1) {
            continenteModulo.reset();
        }

        this.leggeAlfa3();
        this.leggeCapitali();
        this.leggeAlfa2();
        this.leggeContinenti();
        this.leggeUnioneEuropea();

        mappaBeans.values().stream().forEach(bean -> insertSave(bean));
        return RisultatoReset.vuotoMaCostruito;
    }

    /**
     * Caricamento iniziale del legame tra 'nome' e codice 'ISO 3166-1 alpha-3' <br>
     */
    public void leggeAlfa3() {
        String nomePaginaWiki = "ISO 3166-1 alpha-3";
        String testoUtile = VUOTA;
        String tagIni = "{{Div col|strette}}";
        String tagEnd = "{{Div col end}}";
        String testoCompletoPagina = webService.leggeWikiParse(nomePaginaWiki);
        String[] righe = null;
        String sepRiga = ASTERISCO_REGEX;
        String sepParti = SEP;
        String[] parti;
        String nome;
        String alfa3;
        String alfa3Ini = "<kbd>";
        String alfa3End = "</kbd>";
        int pos = 1;

        if (textService.isValid(testoCompletoPagina)) {
            testoUtile = textService.estraeSenza(testoCompletoPagina, tagIni, tagEnd);
            testoUtile = textService.trim(testoUtile);
            if (testoUtile.startsWith(ASTERISCO)) {
                testoUtile = textService.levaTesta(testoUtile, ASTERISCO);
            }
        }

        if (textService.isValid(testoUtile)) {
            righe = testoUtile.split(sepRiga);
        }

        if (righe != null) {
            for (String riga : righe) {
                if (textService.isValid(riga)) {
                    parti = riga.split(sepParti);
                    if (parti != null && parti.length == 2) {
                        alfa3 = parti[0].trim();
                        nome = parti[1].trim();

                        alfa3 = textService.levaTesta(alfa3, alfa3Ini);
                        alfa3 = textService.levaCoda(alfa3, alfa3End);

                        nome = textService.trim(nome);
                        nome = textService.setNoDoppieQuadre(nome);
                        if (nome.contains(PIPE)) {
                            nome = textService.levaPrimaAncheTag(nome, PIPE);
                        }
                        mappaBeans.put(alfa3, newEntity(pos++, nome, alfa3));
                    }
                }
            }
        }
    }

    /**
     * Caricamento delle capitali <br>
     * DEVE esserci già il codice iso3 <br>
     */
    public void leggeCapitali() {
        String nomePaginaWiki = "Capitali degli Stati del mondo";
        String testoTable;
        String[] righeTable;
        String tagTable = "\\|-";
        String[] partiRiga;
        String alfa3;
        String capitale;
        String message;
        StatoEntity entityBean;

        testoTable = webService.leggeWikiTable(nomePaginaWiki);
        testoTable = webService.estraeTable(testoTable);
        righeTable = testoTable.split(tagTable);

        if (righeTable != null) {
            for (String riga : righeTable) {
                riga = textService.levaTesta(riga, TRATTINO);
                riga = textService.levaTesta(riga, CAPO_SPLIT);
                partiRiga = StringUtils.splitByWholeSeparator(riga, CAPO_SPLIT);
                if (partiRiga != null && partiRiga.length > 0) {
                    //                    sottoPartiRiga = StringUtils.split(partiRiga[0], PIPE + PIPE);
                    if (partiRiga != null && partiRiga.length > 2) {
                        alfa3 = partiRiga[0].trim();
                        capitale = partiRiga[1].trim();

                        alfa3 = textService.levaTesta(alfa3, PIPE);
                        capitale = textService.levaTesta(capitale, PIPE);

                        alfa3 = textService.levaCodaDaPrimo(alfa3, SMALL);
                        capitale = textService.levaCodaDaPrimo(capitale, SMALL);

                        alfa3 = textService.setNoDoppieGraffe(alfa3);
                        capitale = textService.setNoDoppieQuadre(capitale);

                        if (alfa3.contains(TRATTINO)) {
                            alfa3 = textService.levaCodaDaPrimo(alfa3, TRATTINO);
                        }

                        if (capitale.contains(PARENTESI_TONDA_END) && capitale.contains(PIPE)) {
                            capitale = textService.levaTesta(capitale, PIPE);
                        }
                        if (capitale.contains(PIPE)) {
                            capitale = textService.levaPrimaAncheTag(capitale, PIPE);
                        }
                        if (capitale.contains(DOPPIE_QUADRE_END) && capitale.contains(DOPPIE_QUADRE_INI)) {
                            capitale = textService.levaCodaDaPrimo(capitale, DOPPIE_QUADRE_END);
                            capitale = textService.levaTesta(capitale, DOPPIE_QUADRE_INI);
                        }

                        if (mappaBeans.containsKey(alfa3)) {
                            entityBean = (StatoEntity) mappaBeans.get(alfa3);
                            entityBean.setCapitale(capitale);
                            mappaBeans.put(alfa3, entityBean);
                        }
                        else {
                            message = String.format("Non ho trovato %s nella riga %s della mappa - leggeCapitali()", alfa3, riga);
                            logger.warn(new WrapLog().message(message).type(TypeLog.reset));
                        }
                    }
                    else {
                        message = String.format("Manca la capitale %s", (Object[]) partiRiga);
                        logger.warn(new WrapLog().message(message).type(TypeLog.reset));
                    }
                }
            }
        }
    }


    /**
     * Caricamento di iso2 e numerico <br>
     * DEVE esserci già il codice iso3 <br>
     */
    public void leggeAlfa2() {
        String nomePaginaWiki = "ISO 3166-1";
        List<List<String>> lista;
        String alfa3;
        String alfa2;
        String numerico;
        String message;
        StatoEntity entityBean;

        lista = webService.getWikiTable(nomePaginaWiki);

        if (lista != null) {
            for (List<String> rigaEntity : lista) {
                numerico = rigaEntity.get(2);
                alfa3 = rigaEntity.get(3);
                alfa2 = rigaEntity.get(4);

                if (mappaBeans.containsKey(alfa3)) {
                    entityBean = (StatoEntity) mappaBeans.get(alfa3);
                    entityBean.setNumerico(numerico);
                    entityBean.setAlfa2(alfa2);
                    entityBean.setDivisioni(TAG_ISO_3166 + alfa2);
                    mappaBeans.put(alfa3, entityBean);
                }
                else {
                    message = String.format("Non ho trovato %s nella mappa - leggeAlfa2()", alfa3);
                    logger.warn(new WrapLog().message(message).type(TypeLog.reset));
                }
            }
        }
    }

    /**
     * Caricamento dei template stati/continente <br>
     */
    public void leggeContinenti() {
        for (ContinenteEnum contEnum : ContinenteEnum.getAllEnums()) {
            leggeContinente(contEnum);
        }
    }

    /**
     * Caricamento del singolo template stati/continente <br>
     */
    public void leggeContinente(ContinenteEnum contEnum) {
        ContinenteEntity continente = null;
        String nomeTemplate;
        String testoLeggibile = VUOTA;
        String testoUtile;
        String prefix = "Template:";
        String tagIni = "|list1";
        String tagEnd = "|group2";
        String sep = "\\{\\{,\\}\\}";
        String[] parti;
        String alfa3;
        String message;
        StatoEntity entityBean;

        continente = continenteModulo.findById(contEnum.getTag());
        if (continente != null && textService.isValid(contEnum.getTemplate())) {
            nomeTemplate = prefix + contEnum.getTemplate();
            testoLeggibile = webService.leggeWikiParse(nomeTemplate);
        }

        if (textService.isValid(testoLeggibile) && testoLeggibile.contains(tagIni)) {
            testoUtile = textService.estraeSenza(testoLeggibile, tagIni, tagEnd);
            testoUtile = textService.levaTesta(testoUtile, UGUALE);
            testoUtile = textService.levaCoda(testoUtile, CAPO_REGEX);

            parti = testoUtile.split(sep);
            if (parti != null) {
                for (String parte : parti) {
                    alfa3 = parte;
                    alfa3 = textService.levaCodaDaPrimo(alfa3, TAG_REF);
                    alfa3 = textService.levaCodaDaPrimo(alfa3, DOPPIE_QUADRE_INI);
                    alfa3 = textService.levaCoda(alfa3, CAPO_SPLIT);
                    alfa3 = textService.setNoDoppieGraffe(alfa3);
                    alfa3 = textService.levaCodaDaPrimo(alfa3, TRATTINO);
                    alfa3 = textService.levaTesta(alfa3, PIPE);
                    if (mappaBeans.containsKey(alfa3)) {
                        entityBean = (StatoEntity) mappaBeans.get(alfa3);
                        entityBean.setContinente(continente);
                        mappaBeans.put(alfa3, entityBean);
                    }
                    else {
                        message = String.format("Non ho trovato %s nella riga %s della mappa - leggeContinente() di %s", alfa3, parte, continente.getNome());
                        logger.warn(new WrapLog().message(message).type(TypeLog.reset));
                    }
                }
            }
        }
    }

    /**
     * Controllo flag appartenenza Unione Europea <br>
     * DEVE esserci già il codice iso3 <br>
     */
    public void leggeUnioneEuropea() {
        String nomePaginaWiki = "Stati membri dell'Unione europea";
        List<List<String>> lista;
        String alfa3;
        String message;
        StatoEntity entityBean;

        lista = webService.getWikiTable(nomePaginaWiki);

        if (lista != null) {
            for (List<String> rigaEntity : lista) {
                alfa3 = rigaEntity.get(0);

                if (mappaBeans.containsKey(alfa3)) {
                    entityBean = (StatoEntity) mappaBeans.get(alfa3);
                    entityBean.setUnioneEuropea(true);
                    mappaBeans.put(alfa3, entityBean);
                }
                else {
                    message = String.format("Non ho trovato %s nella mappa - leggeUnioneEuropea()", alfa3);
                    logger.warn(new WrapLog().message(message).type(TypeLog.reset));
                }
            }
        }
    }

}// end of CrudService class
