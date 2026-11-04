package com.dashboard.populacional.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Municipio extends Regiao {

    private double areaKm2;
    private long populacao;
    private Estado estado;
    private final List<Populacao> historico;

    public Municipio(String nome, double areaKm2) {
        super(nome);
        this.areaKm2 = areaKm2;
        this.populacao = 0L;
        this.historico = new ArrayList<>();
    }

    @Override
    public double getAreaKm2() {
        return areaKm2;
    }

    public void setAreaKm2(double areaKm2) {
        this.areaKm2 = areaKm2;
    }

    @Override
    public long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(long populacao) {
        this.populacao = populacao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Populacao> getHistorico() {
        return Collections.unmodifiableList(historico);
    }

    public void registrarPopulacao(int ano, long quantidade) {
        Populacao registro = new Populacao(this, ano, quantidade);
        historico.add(registro);
        if (populacao == 0 || ano >= obterUltimoAno()) {
            populacao = quantidade;
        }
    }

    private int obterUltimoAno() {
        int ultimo = Integer.MIN_VALUE;
        for (Populacao p : historico) {
            if (p.getAno() > ultimo) {
                ultimo = p.getAno();
            }
        }
        return ultimo;
    }

    public long getPopulacaoNoAno(int ano) {
        for (Populacao p : historico) {
            if (p.getAno() == ano) {
                return p.getQuantidade();
            }
        }
        return -1L;
    }

    @Override
    public String toString() {
        String uf = (estado != null) ? estado.getSigla() : "??";
        return getNome() + "/" + uf + " (pop.: " + populacao + ", área: " + areaKm2 + " km²)";
    }
}
