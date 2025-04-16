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

public class AcoesCronogramaController {
    @FXML private Button backButton;
    @FXML private Button lerCronogramaButton;

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
    private void abrirLerCronograma(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/LerCronogramA.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Cronogramas Disponíveis");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível abrir os cronogramas");
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