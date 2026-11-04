package com.dashboard.populacional.ui;

import com.dashboard.populacional.model.ModeloDados;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AppFX extends Application {

    private Scene cena;
    private ModeloDados modelo = new ModeloDados();

    @Override
    public void start(Stage palco) {
        cena = new Scene(new TelaInicial(this).getRaiz(), 800, 550);
        palco.setTitle("Dashboard Populacional");
        palco.setScene(cena);
        palco.show();
    }

    public ModeloDados getModelo() {
        return modelo;
    }

    public void setModelo(ModeloDados modelo) {
        this.modelo = modelo;
    }

    public Window getJanela() {
        return cena.getWindow();
    }

    public void mostrarTelaInicial() {
        cena.setRoot(new TelaInicial(this).getRaiz());
    }

    public void mostrarTelaCarregamento() {
        cena.setRoot(new TelaCarregamento(this).getRaiz());
    }

    public void mostrarDashboardEstados() {
        cena.setRoot(new TelaDashboardEstados(this).getRaiz());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
