package Test;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ConsultarPropostasController {
    @FXML private Button backButton;

    @FXML
    private void goBack(ActionEvent event) {
        try {
            // Carrega a página intermediária de propostas
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proposta_plantio.fxml"));
            Parent root = loader.load();

            // Obtém a janela atual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Configura a nova cena
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Propostas de Plantio");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar para as propostas de plantio");
        }
    }

    // Adicione aqui outros métodos específicos da consulta de propostas

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}