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
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;

@Component
public class HomeAgricultorController {
    @FXML private Button logoutButton;
    @FXML private Button cronogramasButton;
    @FXML private Button propostasButton;
    @FXML private Button terrenosButton;
    @FXML private Button dadosButton;

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
                cronogramasButton, propostasButton,
                terrenosButton, logoutButton
        };

        for (Button button : buttons) {
            button.setStyle(baseStyle);
            button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
            button.setOnMouseExited(e -> button.setStyle(baseStyle));
        }
    }

    @FXML
    private void abrirCronogramas(ActionEvent event) {
        loadScene(event, "/acoes_cronogramas.fxml", "Ações de Cronogramas");
    }

    @FXML
    private void abrirPropostas(ActionEvent event) {
        loadScene(event, "/proposta_plantio_agricultor.fxml", "Propostas de Plantio");
    }

    @FXML
    private void abrirTerrenos(ActionEvent event) {
        loadScene(event, "/terreno.fxml", "Gestão de Terrenos");
    }

    @FXML
    private void logout(ActionEvent event) {
        loadScene(event, "/login.fxml", "Login");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            URL resource = getClass().getResource(fxmlPath);
            Objects.requireNonNull(resource, "❌ FXML não encontrado: " + fxmlPath);

            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Guardar o estado da janela
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

        } catch (Exception e) {
            showAlert("Erro", "Não foi possível carregar o ecrã: " + title);
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
