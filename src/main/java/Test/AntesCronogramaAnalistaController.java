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

import java.io.IOException;

@Component
public class AntesCronogramaAnalistaController {

    @FXML
    private Button backButton;

    @FXML
    private void goBack(ActionEvent event) {
        carregarPagina(event, "/homepage_analista.fxml", "Homepage Analista");
    }

    private void carregarPagina(ActionEvent event, String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean); // <- obrigatório
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a página.");
            e.printStackTrace();
        }
    }

    private void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void abrirCronogramas(ActionEvent event) {
        carregarPagina(event, "/cronogramas_analista.fxml", "Cronogramas");
    }
}
