

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TrabalhoProjetoApplicationTests extends Application {

	@Override
	public void start(Stage primaryStage) {
		// Create a label with the text "Hello, JavaFX!"
		Label label = new Label("Hello, JavaFX!");

		// Create a scene with the label
		Scene scene = new Scene(label, 300, 200);

		// Set the scene to the stage
		primaryStage.setScene(scene);

		// Set the title of the stage
		primaryStage.setTitle("Hello JavaFX Application");

		// Show the stage
		primaryStage.show();
	}

	public static void main(String[] args) {
		// Launch the JavaFX application
		launch(args);
	}
}
