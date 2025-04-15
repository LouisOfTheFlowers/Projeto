package Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaFXApp extends Application {
    private static final Logger LOGGER = Logger.getLogger(JavaFXApp.class.getName());

    @Override
    public void start(Stage primaryStage) {
        try {
            // Ensure the FXML file is found
            URL fxmlUrl = getClass().getResource("/login.fxml");

            if (fxmlUrl == null) {
                throw new IllegalStateException("FXML file not found: /login.fxml");
            }

            VBox root = FXMLLoader.load(fxmlUrl);

            Scene scene = new Scene(root, 1440, 600);


            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(1440);
            primaryStage.setMinHeight(600);
            primaryStage.show();
        } catch (IOException | IllegalStateException e) {
            // Log the error and show an alert
            LOGGER.log(Level.SEVERE, "Failed to load login screen", e);
            showErrorAlert("Application Error", "Failed to initialize application. " + e.getMessage());
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
