package it.algos.geo.enumeration;

import it.algos.vbase.interfaces.Type;

import java.util.*;
import java.util.stream.*;

/**
 * Project base24
 * Created by Algos
 * User: gac
 * Date: Fri, 10-Nov-2023
 * Time: 14:56
 */
public enum RegioneSpeciali implements Type {
    friuli("IT-36", "Friuli-Venezia Giulia"),
    sardegna("IT-88", "Sardegna"),
    sicilia("IT-82", "Sicilia"),
    trentino("IT-32", "Trentino-Alto Adige,"),
    aosta("IT-23", "Valle d'Aosta"),
    ;

    String sigla;

    String nome;

    /**
     * Costruttore interno della Enumeration <br>
     */
    RegioneSpeciali(final String sigla, final String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }


    public static List<RegioneSpeciali> getAllEnums() {
        return Arrays.stream(values()).toList();
    }

    @Override
    public List<RegioneSpeciali> getAll() {
        return Arrays.stream(values()).toList();
    }

    @Override
    public List<String> getAllTags() {
        return getAllEnums()
                .stream()
                .map(type->type.getTag())
                .collect(Collectors.toList());
    }

    @Override
    public String getTag() {
        return sigla;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }
}
