package Test;

import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.util.Objects;

public class LoginController {
    @FXML
    private ImageView loginImage;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) {
        System.out.println("Login button clicked!"); // Debugging

        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println("Username: " + username + ", Password: " + password); // Debugging

        if ("admin".equals(username) && "password".equals(password)) {
            System.out.println("Login successful! Redirecting...");
            loadHomeScreen();
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }


    private void loadHomeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/home.fxml")));
            Scene homeScene = new Scene(loader.load());

            Stage stage = (Stage) usernameField.getScene().getWindow(); // Get current window
            stage.setScene(homeScene); // Switch to home scene
            stage.setTitle("Home");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load home screen.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void initialize() {

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/novaImagem.jpg")));
        loginImage.setImage(image);
    }
}
