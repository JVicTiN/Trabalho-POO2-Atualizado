package com.dashboard.populacional.app;

import com.dashboard.populacional.io.Entrada;
import com.dashboard.populacional.model.ModeloDados;

import java.util.List;

public class CarregadorModelo {

    public ModeloDados carregar(List<Entrada> entradas) {
        ModeloDados modelo = new ModeloDados();
        for (Entrada entrada : entradas) {
            modelo.registrarPopulacao(
                    entrada.getSiglaEstado(),
                    entrada.getNomeEstado(),
                    entrada.getNomeMunicipio(),
                    entrada.getAreaKm2(),
                    entrada.getAno(),
                    entrada.getPopulacao());
        }
        return modelo;
    }
}
