package Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;  // Import VBox
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class JavaFXApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the FXML file with a VBox as the root
        VBox root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login.fxml")));

        // Create the scene with the VBox root layout
        Scene scene = new Scene(root, 300, 275);

        // Set up the stage and show the scene
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

