package com.dashboard.populacional.indicador;

import com.dashboard.populacional.model.Municipio;

public class CrescimentoPopulacional implements Indicador {

    private final Municipio municipio;
    private final int anoInicial;
    private final int anoFinal;

    public CrescimentoPopulacional(Municipio municipio, int anoInicial, int anoFinal) {
        this.municipio = municipio;
        this.anoInicial = anoInicial;
        this.anoFinal = anoFinal;
    }

    @Override
    public double calcular() {
        long populacaoInicial = municipio.getPopulacaoNoAno(anoInicial);
        long populacaoFinal = municipio.getPopulacaoNoAno(anoFinal);
        if (populacaoInicial <= 0 || populacaoFinal < 0) {
            return 0.0;
        }
        return (populacaoFinal - populacaoInicial) * 100.0 / populacaoInicial;
    }
}
