package com.dashboard.populacional.ui;

import com.dashboard.populacional.app.CarregadorModelo;
import com.dashboard.populacional.io.ArquivoInvalidoException;
import com.dashboard.populacional.io.Entrada;
import com.dashboard.populacional.io.LeitorArquivo;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TelaCarregamento {

    private final VBox raiz = new VBox(15);
    private File arquivoEscolhido;

    public TelaCarregamento(AppFX app) {
        Label titulo = new Label("Carregar novos dados populacionais");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label nomeArquivo = new Label("Nenhum arquivo selecionado.");
        Label mensagem = new Label();

        Button botaoEscolher = new Button("Escolher arquivo...");
        botaoEscolher.setOnAction(e -> {
            FileChooser seletor = new FileChooser();
            seletor.setTitle("Selecione o arquivo de dados");
            seletor.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Arquivos de texto", "*.txt"));
            File pastaDados = new File("dados");
            if (pastaDados.isDirectory()) {
                seletor.setInitialDirectory(pastaDados);
            }
            File escolhido = seletor.showOpenDialog(app.getJanela());
            if (escolhido != null) {
                arquivoEscolhido = escolhido;
                nomeArquivo.setText(escolhido.getName());
                mensagem.setText("");
            }
        });

        Button botaoCarregar = new Button("Carregar");
        botaoCarregar.setOnAction(e -> {
            if (arquivoEscolhido == null) {
                mensagem.setText("Selecione um arquivo primeiro.");
                return;
            }
            try {
                List<Entrada> entradas = new LeitorArquivo().ler(arquivoEscolhido.getPath());
                if (entradas.isEmpty()) {
                    mensagem.setText("O arquivo não possui registros.");
                    return;
                }
                app.setModelo(new CarregadorModelo().carregar(entradas));
                mensagem.setText("Dados carregados com sucesso: " + entradas.size() + " registros.");
            } catch (ArquivoInvalidoException ex) {
                new Alert(Alert.AlertType.ERROR, "Arquivo inválido: " + ex.getMessage()).showAndWait();
            } catch (IOException ex) {
                new Alert(Alert.AlertType.ERROR, "Erro ao ler o arquivo: " + ex.getMessage()).showAndWait();
            }
        });

        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setOnAction(e -> app.mostrarTelaInicial());

        raiz.setAlignment(Pos.CENTER);
        raiz.getChildren().addAll(titulo, nomeArquivo, botaoEscolher, botaoCarregar, mensagem, botaoVoltar);
    }

    public Parent getRaiz() {
        return raiz;
    }
}
