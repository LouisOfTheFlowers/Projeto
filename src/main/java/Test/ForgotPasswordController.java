package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class ForgotPasswordController {

    @FXML
    private TextField emailField;

    @FXML
    private void handleRecoverPassword(ActionEvent event) {
        String input = emailField.getText().trim();

        if (input.isEmpty()) {
            showAlert("Erro", "Por favor, insira o seu e-mail ou nome de utilizador.");
            return;
        }

        showAlert("Instruções Enviadas", "Se o e-mail ou nome estiver correto, irá receber instruções em breve.");
        emailField.clear();
    }

    @FXML
    private void handleBackToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/login.fxml")));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Guardar o estado da janela
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Login");

            // Restaurar o estado da janela
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

        } catch (IOException e) {
            showAlert("Erro", "Não foi possível voltar ao ecrã de login.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
