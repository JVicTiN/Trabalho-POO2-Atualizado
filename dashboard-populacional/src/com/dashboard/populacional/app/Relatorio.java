package com.dashboard.populacional.app;

import com.dashboard.populacional.indicador.CrescimentoPopulacional;
import com.dashboard.populacional.indicador.DensidadeDemografica;
import com.dashboard.populacional.indicador.Indicador;
import com.dashboard.populacional.model.Estado;
import com.dashboard.populacional.model.ModeloDados;
import com.dashboard.populacional.model.Municipio;
import com.dashboard.populacional.model.Regiao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Relatorio {

    private static final int TAMANHO_LOG_EXIBIDO = 5;

    private final ModeloDados modelo;

    public Relatorio(ModeloDados modelo) {
        this.modelo = modelo;
    }

    public void imprimir() {
        imprimirTotais();
        imprimirEstados();
        imprimirMunicipiosPorPopulacao();
        imprimirRegistrosPorRegiao();
        imprimirLogProcessamento();
        imprimirIndicadores();
    }

    private void imprimirIndicadores() {
        System.out.println();
        System.out.println("=== Indicadores (herança com Regiao e interface Indicador) ===");

        List<Regiao> regioes = new ArrayList<>();
        regioes.addAll(modelo.listarEstados());
        regioes.addAll(modelo.listarMunicipios());
        for (Regiao regiao : regioes) {
            Indicador densidade = new DensidadeDemografica(regiao);
            System.out.printf("%s %s - densidade demográfica: %.2f hab/km²%n",
                    regiao.getClass().getSimpleName(), regiao.getNome(), densidade.calcular());
        }

        for (Municipio municipio : modelo.listarMunicipios()) {
            Indicador crescimento = new CrescimentoPopulacional(municipio, 2010, 2020);
            System.out.printf("%s - crescimento 2010-2020: %.2f%%%n",
                    municipio.getNome(), crescimento.calcular());
        }
    }

    private void imprimirTotais() {
        System.out.println();
        System.out.println("Total de estados: " + modelo.totalEstados());
        System.out.println("Total de municípios: " + modelo.totalMunicipios());
    }

    private void imprimirEstados() {
        System.out.println();
        System.out.println("=== Estados (ordenados por população, decrescente) ===");
        for (Estado estado : modelo.listarEstados()) {
            System.out.println(estado);
        }
    }

    private void imprimirMunicipiosPorPopulacao() {
        System.out.println();
        System.out.println("=== Teste de Collections.sort(): municípios por população ===");
        List<Municipio> municipiosOrdenados = modelo.listarMunicipiosPorPopulacao();
        int posicao = 1;
        for (Municipio m : municipiosOrdenados) {
            System.out.println(posicao + ". " + m);
            posicao++;
        }
    }

    private void imprimirRegistrosPorRegiao() {
        System.out.println();
        System.out.println("=== Registros por macrorregião (HashMap + switch) ===");
        for (Map.Entry<String, Integer> par : modelo.getContagemPorRegiao().entrySet()) {
            System.out.println(par.getKey() + ": " + par.getValue() + " registro(s)");
        }
    }

    private void imprimirLogProcessamento() {
        System.out.println();
        System.out.println("=== Log de processamento (Stack) — últimas "
                + TAMANHO_LOG_EXIBIDO + " entradas ===");
        for (String linha : modelo.ultimasEntradasDoLog(TAMANHO_LOG_EXIBIDO)) {
            System.out.println(linha);
        }
    }
}
