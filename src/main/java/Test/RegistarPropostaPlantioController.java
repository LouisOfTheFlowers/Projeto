package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegistarPropostaPlantioController {

    @FXML
    private void registarPropostaPlantio(ActionEvent event) {
        try {
            // Sua lógica para registrar uma nova proposta de plantio
            System.out.println("Registar Proposta de Plantio button clicked!");
            showAlert(Alert.AlertType.INFORMATION, "Registar Proposta de Plantio", "Opening Planting Proposal Registration...");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while registering a planting proposal.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Alterado para voltar para a página intermediária de propostas
        loadScene(event, "/proposta_plantio.fxml", "Propostas de Plantio");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(
                    getClass().getResource(fxmlPath)));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de Navegação",
                    "Não foi possível carregar a página: " + title);
            e.printStackTrace();
        }
    }
}