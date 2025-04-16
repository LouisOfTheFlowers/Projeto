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

public class PropostaPlantioController {
    @FXML private Button backButton;
    @FXML private Button registrarPropostaButton;
    @FXML private Button consultarPropostasButton;

    @FXML
    private void goBack(ActionEvent event) {
        try {
            // Carrega a homepage do agricultor
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/homepage_agricultor.fxml"));
            Parent root = loader.load();

            // Obtém a janela atual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Configura a nova cena
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Área do Agricultor");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar para a homepage");
        }
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
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível carregar a página: " + title);
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