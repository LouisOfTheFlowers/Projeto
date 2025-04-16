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
    private Stage currentStage;

    @FXML
    private void goBack(ActionEvent event) {
        try {
            // Carrega a cena anterior
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proposta_plantio.fxml"));
            Parent root = loader.load();

            // Obtém o stage atual (mesmo stage)
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Define a nova cena no mesmo stage
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível voltar à página anterior");
            e.printStackTrace();
        }
    }

    @FXML
    private void verProposta(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            String propostaId = button.getId().replace("verProposta", "");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dentro_da_Proposta.fxml"));
            Parent root = loader.load();

            DentroDaPropostaController controller = loader.getController();
            controller.setPropostaId(Integer.parseInt(propostaId));

            // Obtém e guarda o stage atual
            currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            controller.setPreviousStage(currentStage);

            // Atualiza a cena no mesmo stage
            currentStage.setScene(new Scene(root));
            currentStage.show();

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir os detalhes da proposta");
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}