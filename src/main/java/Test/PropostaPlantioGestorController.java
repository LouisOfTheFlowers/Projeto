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
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;

@Component
public class PropostaPlantioGestorController {
    @FXML private Button backButton;
    @FXML private Button registrarPropostaButton;
    @FXML private Button consultarPropostasButton;

    @FXML
    private void goBack(ActionEvent event) {
        loadScene(event, "/homepage_gestor.fxml", "Área do Gestor de Produção");
    }

    @FXML
    private void abrirConsultarPropostas(ActionEvent event) {
        loadScene(event, "/ConsultarPropostas.fxml", "Consultar Propostas");
    }

    @FXML
    private void abrirPropostasAnalisadas(ActionEvent event) {
        loadScene(event, "/propostas_analisadas.fxml", "Propostas Analisadas");
    }


    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            URL resource = getClass().getResource(fxmlPath);
            Objects.requireNonNull(resource, "❌ FXML não encontrado: " + fxmlPath);

            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
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
