package Test;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    public void initialize() {
        loginButton.setOnAction(event -> login());
    }

    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("Forthewin".equals(username) && "123".equals(password)) {
            showAlert(AlertType.INFORMATION, "Login Successful", "Welcome " + username);
        } else {
            showAlert(AlertType.ERROR, "Login Failed", "Incorrect username or password");
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

