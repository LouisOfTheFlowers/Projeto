package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class HomeGestorController {

    @FXML private Button logoutButton;
    @FXML private Button propostaButton;
    @FXML private Button analiseButton;
    @FXML private Button relatoriosButton;
    @FXML private Button cronogramaButton;

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
                propostaButton, analiseButton,
                relatoriosButton, cronogramaButton, logoutButton
        };

        for (Button button : buttons) {
            button.setStyle(baseStyle);
            button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
            button.setOnMouseExited(e -> button.setStyle(baseStyle));
        }
    }

    @FXML
    private void abrirPropostaPlantio(ActionEvent event) {
        loadScene(event, "/proposta_plantio_gestor.fxml", "Proposta de Plantio");
    }

    @FXML
    private void abrirAnaliseSolo(ActionEvent event) {
        loadScene(event, "/analise_solo.fxml", "Análise de Solo");
    }

    @FXML
    private void abrirCronograma(ActionEvent event) {
        loadScene(event, "/cronograma_gestor.fxml", "Cronograma");
    }

    @FXML
    private void abrirRelatorio(ActionEvent event) {
        loadScene(event, "/relatorio_gestor.fxml", "Relatório");
    }

    @FXML
    private void logout(ActionEvent event) {
        loadScene(event, "/login.fxml", "Login");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Guardar o estado atual da janela
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle(title);

            // Restaurar o estado da janela
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }
        } catch (IOException | NullPointerException e) {
            showAlert("Erro", "Não foi possível carregar o ecrã: " + title);
            e.printStackTrace();
        }
    }

    private void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
