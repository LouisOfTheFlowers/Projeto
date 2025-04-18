package Test;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
@Component
public class RegistarDadosController {
    @FXML private Button backButton;
    @FXML private TextField campoNomeField;
    @FXML private TextField campoIdadeField;
    @FXML private TextField campoEnderecoField;
    @FXML private TextField campoTelefoneField;

    @FXML
    private void goBack(ActionEvent event) {
        try {
            URL url = getClass().getResource("/dados.fxml");
            if (url == null) {
                throw new IOException("Arquivo dados.fxml não encontrado");
            }

            Parent root = FXMLLoader.load(url);
            loadScene(event, "/Dados.fxml", "Dados");
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível voltar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void registarDados(ActionEvent event) {
        // Implemente aqui a lógica para registrar os dados
        String nome = campoNomeField.getText();
        String idade = campoIdadeField.getText();
        String endereco = campoEnderecoField.getText();
        String telefone = campoTelefoneField.getText();

        // Validação básica
        if (nome.isEmpty() || idade.isEmpty()) {
            showAlert("Erro", "Preencha pelo menos nome e idade");
            return;
        }

        // Processar os dados...
        showAlert("Sucesso", "Dados registrados com sucesso!");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
}