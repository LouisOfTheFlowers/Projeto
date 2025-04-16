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

public class RegistarController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private ImageView registarImage;

    @FXML
    public void initialize() {
        try {
            Image image = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/images/novaImagem.jpg")));
            registarImage.setImage(image);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    @FXML
    private void handleRegistar(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!validateInput(username, password, confirmPassword)) {
            return;
        }

        showAlert(Alert.AlertType.INFORMATION, "Success", "Registration successful!");
        loadScene(event, "/login.fxml", "Login");
    }

    private boolean validateInput(String username, String password, String confirmPassword) {
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords don't match");
            return false;
        }

        if (password.length() < 8) {
            showAlert(Alert.AlertType.ERROR, "Error", "Password must be at least 8 characters");
            return false;
        }

        return true;
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
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load screen");
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
    @FXML
    private void goBack(ActionEvent event) {
        loadScene(event, "/login.fxml", "Homepage Agricultor");
    }
}
