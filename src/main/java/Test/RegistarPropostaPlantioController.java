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
            // Your logic to register a new planting proposal
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
        loadScene(event, "/homepage_agricultor.fxml", "Homepage Agricultor");
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

            e.printStackTrace();
        }
    }
}
