package com.dashboard.populacional.indicador;

import com.dashboard.populacional.model.Regiao;

public class DensidadeDemografica implements Indicador {

    private final Regiao regiao;

    public DensidadeDemografica(Regiao regiao) {
        this.regiao = regiao;
    }

    @Override
    public double calcular() {
        return regiao.getPopulacao() / regiao.getAreaKm2();
    }
}
