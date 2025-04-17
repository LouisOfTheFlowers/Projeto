package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class RegistarController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private ComboBox<String> roleComboBox;

    @FXML
    public void initialize() {
        roleComboBox.getItems().addAll("Agricultor", "Gestor de Producao", "Analista de Dados");
    }

    @FXML
    private void handleRegistar(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String selectedRole = roleComboBox.getValue();

        if (!validateInput(username, password, confirmPassword, selectedRole)) return;

        // Redirecionar para a página específica com base no tipo de utilizador
        switch (selectedRole.toLowerCase()) {
            case "agricultor":
                loadSceneWithData(event, "/registoAgricultor.fxml", "Registo Agricultor");
                break;
            case "gestor de producao":
                loadSceneWithData(event, "/registoGestor.fxml", "Registo Gestor de Produção");
                break;
            case "analista de dados":
                loadSceneWithData(event, "/registoAnalista.fxml", "Registo Analista de Dados");
                break;
            default:
                showAlert(Alert.AlertType.ERROR, "Erro", "Tipo de utilizador inválido.");
        }
    }

    private boolean validateInput(String username, String password, String confirmPassword, String selectedRole) {
        if (username.isEmpty() || password.isEmpty() || selectedRole == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Todos os campos são obrigatórios.");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Erro", "As passwords não coincidem.");
            return false;
        }
        if (password.length() < 8) {
            showAlert(Alert.AlertType.ERROR, "Erro", "A password deve ter pelo menos 8 caracteres.");
            return false;
        }
        return true;
    }

    @FXML
    private void voltarLogin(ActionEvent event) {
        loadScene(event, "/login.fxml", "Login");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar o ecrã: " + title);
            e.printStackTrace();
        }
    }

    private void loadSceneWithData(ActionEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Parent root = loader.load();

            // Aqui podes passar dados ao controller se necessário:
            // Ex: RegistarAgricultorController controller = loader.getController();
            // controller.setUserData(usernameField.getText(), passwordField.getText());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar o ecrã: " + title);
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
