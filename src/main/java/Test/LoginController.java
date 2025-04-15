package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    @FXML private ImageView loginImage;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    public void initialize() {
        try {
            Image image = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/images/novaImagem.jpg")));
            loginImage.setImage(image);
        } catch (NullPointerException e) {
            System.err.println("Failed to load login image: " + e.getMessage());
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Login Failed", "Please enter both username and password");
            return;
        }

        if (authenticate(username, password)) {
            loadScene(event, "/homepage_agricultor.fxml", "Agricultor Dashboard");
        } else {
            showAlert("Login Failed", "Invalid credentials");
        }
    }

    private boolean authenticate(String username, String password) {
        // Replace with proper authentication logic
        return "admin".equals(username) && "password".equals(password);
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(
                    getClass().getResource(fxmlPath)));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            showAlert("Error", "Failed to load screen: " + title);
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRegistarLink(ActionEvent event) {
        loadScene(event, "/registar.fxml", "User Registration");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
