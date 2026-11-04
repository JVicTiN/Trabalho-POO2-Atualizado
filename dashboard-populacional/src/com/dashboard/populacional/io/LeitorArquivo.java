package com.dashboard.populacional.io;

import com.dashboard.populacional.model.Estado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorArquivo {

    private static final int NUMERO_COLUNAS = 6;
    private static final String SEPARADOR = ";";

    public List<Entrada> ler(String caminhoArquivo) throws IOException, ArquivoInvalidoException {
        List<Entrada> entradas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {

            String cabecalho;
            do {
                cabecalho = br.readLine();
            } while (cabecalho != null && cabecalho.trim().isEmpty());

            if (cabecalho == null) {
                throw new ArquivoInvalidoException("Arquivo vazio ou sem cabeçalho.");
            }

            if (cabecalho.split(SEPARADOR, -1).length != NUMERO_COLUNAS) {
                throw new ArquivoInvalidoException("Cabeçalho inválido: esperado "
                        + NUMERO_COLUNAS + " colunas.");
            }

            int numeroLinha = 1;
            String linha;
            while ((linha = br.readLine()) != null) {
                numeroLinha++;

                if (linha.trim().isEmpty()) {
                    continue;
                }

                String[] campos = linha.split(SEPARADOR, -1);

                if (campos.length != NUMERO_COLUNAS) {
                    throw new ArquivoInvalidoException("Linha " + numeroLinha + ": esperado "
                            + NUMERO_COLUNAS + " colunas, encontrado " + campos.length + ".");
                }

                for (int coluna = 0; coluna < campos.length; coluna++) {
                    campos[coluna] = campos[coluna].trim();
                    if (campos[coluna].isEmpty()) {
                        throw new ArquivoInvalidoException("Linha " + numeroLinha + ": coluna "
                                + (coluna + 1) + " está vazia.");
                    }
                }

                String uf = campos[0].toUpperCase();
                String nomeEstado = campos[1];
                String nomeMunicipio = campos[2];
                double area;
                int ano;
                long populacao;

                try {
                    area = Double.parseDouble(campos[3].replace(",", "."));
                    ano = Integer.parseInt(campos[4]);
                    populacao = Long.parseLong(campos[5]);
                } catch (NumberFormatException ex) {
                    throw new ArquivoInvalidoException("Linha " + numeroLinha
                            + ": valor numérico inválido (área, ano ou população).");
                }

                if (area <= 0 || populacao < 0 || ano < 1500 || ano > 2100) {
                    throw new ArquivoInvalidoException("Linha " + numeroLinha
                            + ": valores fora da faixa esperada.");
                }

                String regiaoGeografica = Estado.classificarRegiaoGeografica(uf);
                if (regiaoGeografica.equals("Desconhecida")) {
                    System.out.println("Aviso (linha " + numeroLinha + "): UF '" + uf
                            + "' não reconhecida.");
                }

                entradas.add(new Entrada(uf, nomeEstado, nomeMunicipio, area, ano, populacao));
            }
        }

        return entradas;
    }
}
