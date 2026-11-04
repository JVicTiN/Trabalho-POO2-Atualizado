package com.dashboard.populacional.io;
public class Entrada {

    private String siglaEstado;
    private String nomeEstado;
    private String nomeMunicipio;
    private double areaKm2;
    private int ano;
    private long populacao;

    public Entrada(String siglaEstado, String nomeEstado, String nomeMunicipio,
                    double areaKm2, int ano, long populacao) {
        this.siglaEstado = siglaEstado;
        this.nomeEstado = nomeEstado;
        this.nomeMunicipio = nomeMunicipio;
        this.areaKm2 = areaKm2;
        this.ano = ano;
        this.populacao = populacao;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public double getAreaKm2() {
        return areaKm2;
    }

    public void setAreaKm2(double areaKm2) {
        this.areaKm2 = areaKm2;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(long populacao) {
        this.populacao = populacao;
    }

    @Override
    public String toString() {
        return siglaEstado + ";" + nomeEstado + ";" + nomeMunicipio + ";" + areaKm2 + ";" + ano + ";" + populacao;
    }
}
