package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class HomeController {

    @FXML
    private void handleLogout(ActionEvent event) {
        loadScene(event, "/login.fxml", "Login");
    }

    private void loadHomePage(ActionEvent event) {
        loadScene(event, "/homePage.fxml", "Home");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Guardar o estado da janela
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle(title);

            // Restaurar o estado da janela
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
