package com.dashboard.populacional.ui;

import com.dashboard.populacional.model.ModeloDados;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TelaInicial {

    private final VBox raiz = new VBox(15);

    public TelaInicial(AppFX app) {
        ModeloDados modelo = app.getModelo();

        Label titulo = new Label("Dashboard Populacional");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        String textoStatus = modelo.estaVazio()
                ? "Nenhum dado carregado."
                : "Dados carregados: " + modelo.totalEstados() + " estados, "
                        + modelo.totalMunicipios() + " municípios.";
        Label status = new Label(textoStatus);

        Button botaoDashboard = new Button("Dashboard: população por estado");
        botaoDashboard.setPrefWidth(280);
        botaoDashboard.setDisable(modelo.estaVazio());
        botaoDashboard.setOnAction(e -> app.mostrarDashboardEstados());

        Button botaoCarregar = new Button("Carregar novos dados");
        botaoCarregar.setPrefWidth(280);
        botaoCarregar.setOnAction(e -> app.mostrarTelaCarregamento());

        raiz.setAlignment(Pos.CENTER);
        raiz.getChildren().addAll(titulo, status, botaoDashboard, botaoCarregar);
    }

    public Parent getRaiz() {
        return raiz;
    }
}
