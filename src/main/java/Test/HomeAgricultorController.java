package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomeAgricultorController {
    @FXML private Button backButton;
    @FXML private Button lerCronogramaButton;
    @FXML private Button registarPropostaPlantioButton;
    @FXML private Button registarDadosButton;
    @FXML private Button registarTerrenoButton;
    @FXML private Button consultarPropostasButton;

    @FXML
    public void initialize() {
        setupButtonStyles();
    }

    private void setupButtonStyles() {
        String baseStyle = "-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; "
                + "-fx-border-radius: 5; -fx-padding: 10 20; -fx-font-weight: bold;";
        String hoverStyle = "-fx-font-size: 18px; -fx-background-color: #45a049; -fx-text-fill: white; "
                + "-fx-border-radius: 5; -fx-padding: 10 20; -fx-font-weight: bold;";

        Button[] buttons = {
                lerCronogramaButton, registarPropostaPlantioButton,
                registarDadosButton, registarTerrenoButton,
                consultarPropostasButton, backButton
        };

        for (Button button : buttons) {
            button.setStyle(baseStyle);
            button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
            button.setOnMouseExited(e -> button.setStyle(baseStyle));
        }
    }

    @FXML
    private void lerCronograma(ActionEvent event) {
        loadScene(event, "/LerCronogramA.fxml", "Cronograma");
    }

    @FXML
    private void registarPropostaPlantio(ActionEvent event) {
        loadScene(event, "/registar_proposta_plantio.fxml", "Proposta de Plantio");
    }

    @FXML
    private void registarDados(ActionEvent event) {
        loadScene(event, "/RegistarDados.fxml", "Registro de Dados");
    }

    @FXML
    private void registarTerreno(ActionEvent event) {
        loadScene(event, "/registar_terreno.fxml", "Registro de Terreno");
    }

    @FXML
    private void consultarPropostas(ActionEvent event) {
        loadScene(event, "/ConsultarPropostas.fxml", "Consulta de Propostas");
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadScene(event, "/login.fxml", "Login");
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
            showAlert("Error", "Failed to load screen: " + title);
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
