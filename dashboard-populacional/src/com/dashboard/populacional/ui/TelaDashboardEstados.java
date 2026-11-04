package com.dashboard.populacional.ui;

import com.dashboard.populacional.model.Estado;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class TelaDashboardEstados {

    private final BorderPane raiz = new BorderPane();

    public TelaDashboardEstados(AppFX app) {
        CategoryAxis eixoX = new CategoryAxis();
        eixoX.setLabel("Estado");
        NumberAxis eixoY = new NumberAxis();
        eixoY.setLabel("População");

        BarChart<String, Number> grafico = new BarChart<>(eixoX, eixoY);
        grafico.setTitle("População total por estado");
        grafico.setLegendVisible(false);

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        for (Estado estado : app.getModelo().listarEstados()) {
            serie.getData().add(new XYChart.Data<>(estado.getSigla(), estado.getPopulacao()));
        }
        grafico.getData().add(serie);

        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setOnAction(e -> app.mostrarTelaInicial());
        BorderPane.setAlignment(botaoVoltar, Pos.CENTER);
        BorderPane.setMargin(botaoVoltar, new Insets(10));

        raiz.setCenter(grafico);
        raiz.setBottom(botaoVoltar);
    }

    public Parent getRaiz() {
        return raiz;
    }
}
