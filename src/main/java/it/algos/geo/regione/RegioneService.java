package it.algos.geo.regione;

import it.algos.geo.logic.*;
import it.algos.geo.stato.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.boot.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.backend.exception.*;
import it.algos.vbase.backend.logic.*;
import it.algos.vbase.backend.wrapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * Project base24
 * Created by Algos
 * User: gac
 * Date: Tue, 07-Nov-2023
 * Time: 16:47
 */
@Service
public class RegioneService extends GeoModuloService {

    @Value("${algos.project.crea.directory.geo:false}")
    private String creaDirectoryGeoTxt;

    @Autowired
    public StatoService statoModulo;

    private String nome;

    private String sigla;

    private RegioneEntity entityBean;

    private List<List<String>> listaBean;

    /**
     * Costruttore invocato dalla sottoclasse concreta obbligatoriamente con due parametri <br>
     * Regola la entityClazz associata a questo Modulo <br>
     * Regola la viewClazz @Route associata a questo Modulo <br>
     */
    public RegioneService() {
        super(RegioneEntity.class, RegioneView.class);
    }


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata <br>
     *
     * @return la nuova entity appena creata (con keyID ma non salvata)
     */
    public RegioneEntity newEntity(int ordine, String code, String sigla, StatoEntity stato, String pagina, TypeRegione type) {
        RegioneEntity newEntityBean = RegioneEntity.builder()
                .code(textService.isValid(code) ? code : null)
                .sigla(textService.isValid(sigla) ? sigla : null)
                .stato(stato)
                .pagina(textService.isValid(pagina) ? pagina : null)
                .type(type)
                .build();

        newEntityBean.setOrdine(ordine == 0 ? nextOrdine() : ordine);
        return newEntityBean;
    }

    @Override
    public List<RegioneEntity> findAll() {
        return super.findAll();
    }

    @Override
    public RegioneEntity findByCode(final String keyPropertyValue) {
        return (RegioneEntity) super.findByCode(keyPropertyValue);
    }

    public RegioneEntity findOneByProperty(String keyPropertyName, Object keyPropertyValue) {
        return (RegioneEntity) super.findOneByProperty(keyPropertyName, keyPropertyValue);
    }

    public List<RegioneEntity> findAllItalia() {
        StatoEntity italia = statoModulo.findByCode("Italia");
        if (italia == null) {
            return new ArrayList<>();
        }

        Query query = new Query();
        Sort sort = Sort.by(Sort.Direction.ASC, "ordine");
        query.addCriteria(Criteria.where("stato").is(italia));
        query.addCriteria(Criteria.where("type").in(TypeRegione.regione, TypeRegione.regioneSpeciale));
        query.with(sort);

        List<AbstractEntity> lista = mongoService.mongoTemplate.find(query, moduloCrudEntityClazz, "regione");
        return lista.stream().map(bean -> (RegioneEntity) bean).toList();
    }


    @Override
    public void download() {
        reset();
    }


    public RisultatoReset reset() {
        if (!Boolean.parseBoolean(creaDirectoryGeoTxt)) {
            return RisultatoReset.nonCostruito;
        }

        if (!BaseVar.creaDirectoryGeografia) {
            addItaliaOnly();
            mappaBeans.values().stream().forEach(bean -> insertSave(bean));
            return RisultatoReset.vuotoMaCostruito;
        }

        int pos;
        pos = addItalia();
        pos = addFrancia(pos);
        pos = add("Svizzera", TypeRegione.cantone, pos);
        pos = add("Austria", TypeRegione.land, pos);
        pos = add("Germania", TypeRegione.land, pos);
        pos = addSpagna(pos);
        pos = addPortogallo(pos);
        pos = add("Slovenia", TypeRegione.comune, pos);
        pos = add("Albania", TypeRegione.prefettura, pos);
        pos = add("Andorra", TypeRegione.parrocchia, pos);
        pos = addArmenia(pos);
        pos = addBelgio(pos);
        pos = add("Bulgaria", TypeRegione.distretto, pos);
        pos = add("Bosnia ed Erzegovina", TypeRegione.entita, pos);
        pos = add("Grecia", TypeRegione.periferia, pos);
        pos = addCechia(pos);
        pos = add("Danimarca", TypeRegione.regione, pos);
        pos = add("Croazia", TypeRegione.regione, pos);
        pos = addUngheria(pos);
        pos = add("Liechtenstein", TypeRegione.comune, pos);
        pos = add("Lussemburgo", TypeRegione.cantone, pos);
        pos = add("Principato di Monaco", TypeRegione.quartiere, pos);
        pos = addOlanda(pos);
        pos = add("Polonia", TypeRegione.regione, pos);
        pos = add("Slovacchia", TypeRegione.regione, pos);
        pos = addRomania(pos);
        pos = addSerbia(pos);
        pos = add("Macedonia del Nord", TypeRegione.comune, pos);
        pos = add("Ucraina", TypeRegione.oblast, pos);

        mappaBeans.values().stream().forEach(bean -> insertSave(bean));
        return RisultatoReset.vuotoMaCostruito;
    }

    public int add(String nomeStato, TypeRegione type, int pos) {
        StatoEntity stato = getStato(nomeStato);
        listaBean = this.getLista(stato, 1);

        for (List<String> rigaArray : listaBean) {
            pos = pos + 1;
            sigla = rigaArray.get(0);
            nome = rigaArray.get(1);

            if (nome.equals(sigla)) {
                nome = webService.getNomeDaLink(nome);
            }
            if (nome.contains(TRATTINO)) {
                String iniSigla = textService.levaCodaDaPrimo(sigla, TRATTINO);
                String iniNome = textService.levaCodaDaPrimo(nome, TRATTINO);
                if (iniNome.equals(iniSigla)) {
                    nome = webService.getNomeDaLink(nome);
                }
            }

            entityBean = newEntity(pos, nome, sigla, getStato(nomeStato), TAG_ISO_3166 + stato.getAlfa2(), type);
            if (entityBean != null) {
                mappaBeans.put(sigla, entityBean);
            }
        }

        return pos;
    }

    public void addItaliaOnly() {
        String nomeStato = "Italia";
        StatoEntity stato = this.getStato(nomeStato);

        if (stato == null) {
            stato = (StatoEntity) statoModulo.creaIfNotExists(statoModulo.newEntity("Italia", "Roma", "ITA", "IT"));
        }

        //--regioni
        listaBean = this.getLista(stato, 1);
        add(stato, listaBean, 0, TypeRegione.regione);
        for (RegioneSpeciali regio : RegioneSpeciali.values()) {
            entityBean = (RegioneEntity) mappaBeans.get(regio.getSigla());
            entityBean.setType(TypeRegione.regioneSpeciale);
            mappaBeans.put(regio.getSigla(), entityBean);
        }
    }


    public int addItalia() {
        int pos = 0;
        String nomeStato = "Italia";
        StatoEntity stato = this.getStato(nomeStato);

        if (stato == null) {
            return 0;
        }

        //--regioni
        listaBean = this.getLista(stato, 1);
        pos = add(stato, listaBean, pos, TypeRegione.regione);
        for (RegioneSpeciali regio : RegioneSpeciali.values()) {
            entityBean = (RegioneEntity) mappaBeans.get(regio.getSigla());
            entityBean.setType(TypeRegione.regioneSpeciale);
            mappaBeans.put(regio.getSigla(), entityBean);
        }
        //        entityBean = (RegioneEntity) mappaBeans.get("IT-36");
        //        entityBean.setType(TypeRegione.regioneSpeciale);

        //        //--città metropolitane
        //        listaBean = this.getLista(stato, 2);
        //        pos = add(stato, listaBean, pos, TypeRegione.cittaMetropolitana);

        //        //--province
        //        listaBean = this.getLista(stato, 3);
        //        pos = add(stato, listaBean, pos, TypeRegione.provincia);

        return pos;
    }


    public int addFrancia(int pos) {
        String nomeStato = "Francia";
        StatoEntity stato = getStato(nomeStato);

        //--regioni
        listaBean = this.getLista(stato, 1);
        pos = add(stato, listaBean, pos, TypeRegione.regioneMetropolitana);

        //--collettività
        listaBean = this.getLista(stato, 2);
        pos = add(stato, listaBean, pos, TypeRegione.collettivitaSpeciale);
        entityBean = (RegioneEntity) mappaBeans.get("FR-6AE");
        entityBean.setType(TypeRegione.collettivitaEuropea);
        mappaBeans.put("FR-6AE", entityBean);

        //--dipartimenti
        listaBean = this.getLista(stato, 3);
        pos = add(stato, listaBean, pos, TypeRegione.dipartimentoMetropolitano);

        //--dipartimenti d'oltremare
        listaBean = this.getLista(stato, 4);
        pos = add(stato, listaBean, pos, TypeRegione.dipartimentoOltremare);

        //--collettività d'oltremare
        listaBean = this.getLista(stato, 5);
        pos = add(stato, listaBean, pos, TypeRegione.collettivitaOltremare);
        return pos;
    }

    public int addSpagna(int pos) {
        String nomeStato = "Spagna";
        StatoEntity stato = getStato(nomeStato);

        //--comunità
        listaBean = this.getLista(stato, 1);
        pos = add(stato, listaBean, pos, TypeRegione.comunitaAutonoma);

        //--città autonome
        listaBean = this.getLista(stato, 2);
        pos = add(stato, listaBean, pos, TypeRegione.cittaAutonoma);

        //--province
        listaBean = this.getLista(stato, 3);
        pos = add(stato, listaBean, pos, TypeRegione.provincia);

        return pos;
    }

    public int addPortogallo(int pos) {
        String nomeStato = "Portogallo";
        StatoEntity stato = getStato(nomeStato);

        //--distretto
        listaBean = this.getLista(stato, 1);
        pos = add(stato, listaBean, pos, TypeRegione.distretto);

        //--regione autonome
        listaBean = this.getLista(stato, 2);
        pos = add(stato, listaBean, pos, TypeRegione.regioneAutonoma);

        return pos;
    }

    public int addArmenia(int pos) {
        String nomeStato = "Armenia";
        StatoEntity stato = getStato(nomeStato);

        //--provincia
        listaBean = this.getLista(stato, 1);
        pos = add(stato, listaBean, pos, TypeRegione.provincia);

        //--capitale
        entityBean = (RegioneEntity) mappaBeans.get("AM-ER");
        entityBean.setType(TypeRegione.capitale);
        mappaBeans.put("AM-ER", entityBean);

        return pos;
    }

    public int addBelgio(int pos) {
        String nomeStato = "Belgio";
        StatoEntity stato = getStato(nomeStato);

        //--regione
        listaBean = this.getLista(stato, 1);
        pos = add(stato, listaBean, pos, TypeRegione.regione);

        //--provincia
        listaBean = this.getLista(stato, 2);
        pos = add(stato, listaBean, pos, TypeRegione.provincia);

        return pos;
    }

    public int addCechia(int pos) {
        String nomeStato = "Repubblica Ceca";
        StatoEntity stato = getStato(nomeStato);

        //--regioni
        listaBean = this.getLista(stato, 1);
        pos = add(stato, listaBean, pos, TypeRegione.regione);

        //--distretti
        listaBean = this.getLista(stato, 2);
        pos = add(stato, listaBean, pos, TypeRegione.distretto);

        return pos;
    }


    public int addUngheria(int pos) {
        String nomeStato = "Ungheria";
        StatoEntity stato = getStato(nomeStato);

        //--contee
        listaBean = this.getLista(stato, 1);
        pos = add(stato, listaBean, pos, TypeRegione.contea);

        //--città comitali
        listaBean = this.getLista(stato, 2);
        pos = add(stato, listaBean, pos, TypeRegione.cittaComitale);

        return pos;
    }


    public int addOlanda(int pos) {
        String nomeStato = "Paesi Bassi";
        StatoEntity stato = getStato(nomeStato);

        //--province
        listaBean = this.getLista(stato, 1);
        pos = add(stato, listaBean, pos, TypeRegione.provincia);

        //--nazioni costitutive
        listaBean = this.getLista(stato, 2);
        for (List<String> rigaArray : listaBean.subList(0, 3)) {
            pos = pos + 1;
            sigla = rigaArray.get(0);
            nome = rigaArray.get(1);

            for (TemplateBandierina bandierina : TemplateBandierina.values()) {
                if (nome.equals(bandierina.name())) {
                    nome = bandierina.getTag();
                    break;
                }
            }
            entityBean = newEntity(pos, nome, sigla, stato, stato.getAlfa2() + "xx", TypeRegione.nazione);
            if (entityBean != null) {
                mappaBeans.put(sigla, entityBean);
            }
        }

        //--municipalità speciale
        listaBean = this.getLista(stato, 2);
        for (List<String> rigaArray : listaBean.subList(3, 6)) {
            pos = pos + 1;
            sigla = rigaArray.get(0);
            nome = textService.levaPrimaAncheTag(sigla, TRATTINO);
            for (TemplateBandierina bandierina : TemplateBandierina.values()) {
                if (nome.equals(bandierina.name())) {
                    nome = bandierina.getTag();
                    break;
                }
            }
            entityBean = newEntity(pos, nome, sigla, stato, TAG_ISO_3166 + stato.getAlfa2(), TypeRegione.municipalita);
            if (entityBean != null) {
                mappaBeans.put(sigla, entityBean);
            }
        }

        return pos;
    }


    public int addRomania(int pos) {
        String nomeStato = "Romania";
        StatoEntity stato = getStato(nomeStato);

        //--distretto
        listaBean = this.getLista(stato, 1);
        pos = add(stato, listaBean, pos, TypeRegione.distretto);

        //--città capitale
        listaBean = this.getLista(stato, 2);
        pos = add(stato, listaBean, pos, TypeRegione.capitale);

        return pos;
    }


    public int addSerbia(int pos) {
        String nomeStato = "Serbia";
        StatoEntity stato = getStato(nomeStato);

        //--province
        listaBean = this.getLista(stato, 1);
        pos = add(stato, listaBean, pos, TypeRegione.provincia);

        //--distretti
        listaBean = this.getLista(stato, 2);
        pos = add(stato, listaBean, pos, TypeRegione.distretto);

        return pos;
    }

    public int add(StatoEntity stato, List<List<String>> listaBean, int pos, TypeRegione type) {
        if (listaBean != null) {
            for (List<String> rigaArray : listaBean) {
                pos = pos + 1;
                sigla = rigaArray.get(0);
                nome = rigaArray.get(1);

                //--le sigle col trattino (corte) vanno elaborate
                //--i nomi composti (lunghi) come Emilia-Romagna eo Friuli-Venezia Giulia, no
                if (nome.contains(TRATTINO) && nome.length() < 10) {
                    nome = webService.getNomeDaLink(nome);
                }

                //-- sigle della Francia
                for (TemplateBandierina bandierina : TemplateBandierina.values()) {
                    if (nome.equals(bandierina.name())) {
                        nome = bandierina.getTag();
                        break;
                    }
                }

                entityBean = newEntity(pos, nome, sigla, stato, TAG_ISO_3166 + stato.getAlfa2(), type);
                if (entityBean != null) {
                    mappaBeans.put(sigla, entityBean);
                }
            }
        }

        return pos;
    }


    private StatoEntity getStato(String nomeStato) {
        StatoEntity stato = null;
        String message;

        if (textService.isEmpty(nomeStato)) {
            message = String.format("Nel metodo manca il nome dello stato", nomeStato);
            logger.error(new WrapLog().exception(new AlgosException(message)).type(TypeLog.startup));
            return stato;
        }
        nomeStato = textService.primaMaiuscola(nomeStato);
        stato = statoModulo.findByCode(nomeStato);

        if (stato == null) {
            message = String.format("Non ho trovato lo stato [%s]", nomeStato);
            logger.error(new WrapLog().exception(new AlgosException(message)).type(TypeLog.startup));
            return stato;
        }

        return stato;
    }

    private List<List<String>> getLista(StatoEntity stato, int pos) {
        List<List<String>> lista = null;
        String nomePaginaWiki;
        String message;
        if (stato == null) {
            return lista;
        }

        nomePaginaWiki = TAG_ISO_3166 + stato.getAlfa2();
        if (textService.isEmpty(nomePaginaWiki)) {
            message = String.format("Nello stato [%s] manca la property 'alfa2'", stato);
            logger.error(new WrapLog().exception(new AlgosException(message)).type(TypeLog.startup));
            return lista;
        }

        return webService.getWikiTable(nomePaginaWiki, pos);
    }

}// end of CrudService class
