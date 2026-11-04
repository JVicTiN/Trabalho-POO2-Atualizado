package com.dashboard.populacional.app;

import com.dashboard.populacional.io.ArquivoInvalidoException;
import com.dashboard.populacional.io.Entrada;
import com.dashboard.populacional.io.LeitorArquivo;
import com.dashboard.populacional.model.ModeloDados;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final String CAMINHO_PADRAO = "dados/dados_populacionais.txt";

    public static void main(String[] args) throws IOException {
        String caminhoArquivo = (args.length > 0) ? args[0] : CAMINHO_PADRAO;

        List<Entrada> entradas;
        try {
            entradas = new LeitorArquivo().ler(caminhoArquivo);
        } catch (ArquivoInvalidoException ex) {
            System.out.println("Arquivo inválido: " + ex.getMessage());
            return;
        }

        if (entradas.isEmpty()) {
            System.out.println("Nenhum registro válido foi lido do arquivo.");
            return;
        } else {
            System.out.println("Entradas lidas: " + entradas.size());
        }

        ModeloDados modelo = new CarregadorModelo().carregar(entradas);

        new Relatorio(modelo).imprimir();
    }
}
