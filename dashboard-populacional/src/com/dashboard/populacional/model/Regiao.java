package com.dashboard.populacional.model;

public abstract class Regiao {

    private String nome;

    public Regiao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract long getPopulacao();

    public abstract double getAreaKm2();
}
