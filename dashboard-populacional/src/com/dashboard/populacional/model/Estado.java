package com.dashboard.populacional.model;

import java.util.ArrayList;
import java.util.List;

public class Estado extends Regiao {

    private static final String REGIAO_DESCONHECIDA = "Desconhecida";

    private String sigla;
    private final List<Municipio> municipios;

    public Estado(String sigla, String nome) {
        super(nome);
        this.sigla = sigla;
        this.municipios = new ArrayList<>();
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Municipio> getMunicipios() {
        return municipios;
    }

    public void adicionarMunicipio(Municipio municipio) {
        municipios.add(municipio);
        municipio.setEstado(this);
    }

    @Override
    public long getPopulacao() {
        long total = 0L;
        for (Municipio m : municipios) {
            total += m.getPopulacao();
        }
        return total;
    }

    @Override
    public double getAreaKm2() {
        double total = 0.0;
        for (Municipio m : municipios) {
            total += m.getAreaKm2();
        }
        return total;
    }

    public Municipio buscarMunicipio(String nomeMunicipio) {
        for (Municipio m : municipios) {
            if (m.getNome().equalsIgnoreCase(nomeMunicipio)) {
                return m;
            }
        }
        return null;
    }

    public String getMacrorregiao() {
        return classificarRegiaoGeografica(sigla);
    }

    public static String classificarRegiaoGeografica(String uf) {
        switch (uf) {
            case "SP":
            case "RJ":
            case "MG":
            case "ES":
                return "Sudeste";
            case "PR":
            case "SC":
            case "RS":
                return "Sul";
            case "BA":
            case "PE":
            case "CE":
            case "MA":
            case "PB":
            case "RN":
            case "AL":
            case "SE":
            case "PI":
                return "Nordeste";
            case "GO":
            case "MT":
            case "MS":
            case "DF":
                return "Centro-Oeste";
            case "AM":
            case "PA":
            case "AC":
            case "RO":
            case "RR":
            case "AP":
            case "TO":
                return "Norte";
            default:
                return REGIAO_DESCONHECIDA;
        }
    }

    @Override
    public String toString() {
        return sigla + " - " + getNome() + " (" + municipios.size() + " municípios, pop.: "
                + getPopulacao() + ")";
    }
}
