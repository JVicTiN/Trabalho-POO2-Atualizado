package com.dashboard.populacional.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
public class ModeloDados {

    private final Map<String, Estado> estadosPorSigla = new HashMap<>();
    private final List<Municipio> todosMunicipios = new ArrayList<>();
    private final Map<String, Integer> contagemPorRegiao = new HashMap<>();
    private final Stack<String> logProcessamento = new Stack<>();

    public Estado obterOuCriarEstado(String sigla, String nome) {
        Estado estado = estadosPorSigla.get(sigla);
        if (estado == null) {
            estado = new Estado(sigla, nome);
            estadosPorSigla.put(sigla, estado);
        }
        return estado;
    }

    public void adicionarMunicipio(Municipio municipio) {
        todosMunicipios.add(municipio);
    }

    public List<Estado> listarEstados() {
        List<Estado> lista = new ArrayList<>(estadosPorSigla.values());
        // Collections.sort() exigido pelo enunciado (ordena por população total, decrescente)
        Collections.sort(lista, new Comparator<Estado>() {
            @Override
            public int compare(Estado a, Estado b) {
                return Long.compare(b.getPopulacao(), a.getPopulacao());
            }
        });
        return lista;
    }

    public List<Municipio> listarMunicipios() {
        return new ArrayList<>(todosMunicipios);
    }

    public List<Municipio> listarMunicipiosPorPopulacao() {
        List<Municipio> copia = new ArrayList<>(todosMunicipios);
        Collections.sort(copia, (m1, m2) -> Long.compare(m2.getPopulacao(), m1.getPopulacao()));
        return copia;
    }

    public void registrarPopulacao(String siglaEstado, String nomeEstado, String nomeMunicipio,
                                   double areaKm2, int ano, long populacao) {
        Estado estado = obterOuCriarEstado(siglaEstado, nomeEstado);

        Municipio municipio = estado.buscarMunicipio(nomeMunicipio);
        if (municipio == null) {
            municipio = new Municipio(nomeMunicipio, areaKm2);
            estado.adicionarMunicipio(municipio);
            adicionarMunicipio(municipio);
        }
        municipio.registrarPopulacao(ano, populacao);

        contagemPorRegiao.merge(Estado.classificarRegiaoGeografica(siglaEstado), 1, Integer::sum);
        logProcessamento.push("Processado: " + nomeMunicipio + "/" + siglaEstado + " (" + ano + ")");
    }

    public Map<String, Integer> getContagemPorRegiao() {
        return Collections.unmodifiableMap(contagemPorRegiao);
    }

    public List<String> ultimasEntradasDoLog(int quantidade) {
        List<String> ultimas = new ArrayList<>();
        Stack<String> copia = new Stack<>();
        copia.addAll(logProcessamento);
        while (!copia.empty() && ultimas.size() < quantidade) {
            ultimas.add(copia.pop());
        }
        return ultimas;
    }

    public boolean estaVazio() {
        return todosMunicipios.isEmpty();
    }

    public int totalEstados() {
        return estadosPorSigla.size();
    }

    public int totalMunicipios() {
        return todosMunicipios.size();
    }
}
