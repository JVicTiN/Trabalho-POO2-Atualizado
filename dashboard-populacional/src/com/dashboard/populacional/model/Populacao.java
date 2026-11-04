package com.dashboard.populacional.model;
public class Populacao {

    private Municipio municipio;
    private int ano;
    private long quantidade;

    Populacao(Municipio municipio, int ano, long quantidade) {
        this.municipio = municipio;
        this.ano = ano;
        this.quantidade = quantidade;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return ano + ": " + quantidade + " hab.";
    }
}
