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
import java.util.Objects;

public class PropostaPlantioController {
    @FXML private Button backButton;
    @FXML private Button registrarPropostaButton;
    @FXML private Button consultarPropostasButton;

    @FXML
    private void goBack(ActionEvent event) {

        loadScene(event, "/homepage_agricultor.fxml", "Área do Agricultor");
    }

    @FXML
    private void abrirRegistrarProposta(ActionEvent event) {
        loadScene(event, "/registar_proposta_plantio.fxml", "Registrar Proposta de Plantio");
    }

    @FXML
    private void abrirConsultarPropostas(ActionEvent event) {
        loadScene(event, "/ConsultarPropostas.fxml", "Consultar Propostas");
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}