package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;

@Component
public class RegistarPropostaPlantioController {

    @FXML
    private void registarPropostaPlantio(ActionEvent event) {
        try {
            // Sua lógica para registrar uma nova proposta de plantio
            System.out.println("Registar Proposta de Plantio button clicked!");
            showAlert(Alert.AlertType.INFORMATION, "Registar Proposta de Plantio", "Opening Planting Proposal Registration...");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro ao registar a proposta de plantio.");
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
        loadScene(event, "/proposta_plantio_agricultor.fxml", "Propostas de Plantio");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            URL resource = getClass().getResource(fxmlPath);
            Objects.requireNonNull(resource, "❌ FXML não encontrado: " + fxmlPath);

            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle(title);

            // Restaura o estado da janela
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro", "Ocorreu um erro ao carregar a página.");
        }
    }
}
