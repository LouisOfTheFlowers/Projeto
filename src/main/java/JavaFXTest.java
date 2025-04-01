import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXTest extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        Label label = new Label("JavaFX is working!");
        Button button = new Button("Click me!");

        // Style components
        label.setStyle("-fx-font-size: 20px; -fx-text-fill: #0066cc;");
        button.setStyle("-fx-base: #4CAF50; -fx-font-weight: bold;");

        // Button action
        button.setOnAction(e -> {
            label.setText("Button clicked! ✓");
            label.setStyle("-fx-text-fill: #009933;");
        });

        // Layout
        VBox root = new VBox(20, label, button);
        root.setStyle("-fx-padding: 30; -fx-alignment: center;");

        // Setup scene and stage
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("JavaFX Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}