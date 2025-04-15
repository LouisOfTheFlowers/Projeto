package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button logoutButton;

    @FXML
    private void handleLogout(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Scene loginScene = new Scene(loader.load(), 1440, 600);
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(loginScene);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadHomePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/homePage.fxml"));
            Scene homeScene = new Scene(loader.load(), 1440, 600);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(homeScene);
            stage.setTitle("Home");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
