package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegistarController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private ImageView registarImage;


    private Stage currentStage;  // Guarda referência ao stage atual

    @FXML
    public void initialize() {
        try {
            Image image = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream("/images/novaImagem.jpg")));
            registarImage.setImage(image);
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem: " + e.getMessage());
        }
    }

    @FXML
    private void handleRegistar(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Erro", "Todos os campos são obrigatórios.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Erro", "As palavras-passe não coincidem.");
            return;
        }

        // Lógica de registro...
        showAlert("Sucesso", "Registo concluído com sucesso!");

        // Fecha completamente a janela de registro
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();

        // Reabre a janela de login (opcional)
        abrirJanelaLogin();
    }

    private void abrirJanelaLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(root));
            loginStage.show();
        } catch (IOException e) {
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

    public void setStageAndSetupListeners(Stage stage) {
        this.currentStage = stage;  // Guarda a referência ao stage atual

        // Configurar comportamento de redimensionamento
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            double newWidth = newVal.doubleValue();
            registarImage.setFitWidth(newWidth * 0.3);
        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            double newHeight = newVal.doubleValue();
            registarImage.setFitHeight(newHeight * 0.5);
        });

        // Configurar tamanho mínimo
        stage.setMinWidth(600);
        stage.setMinHeight(400);
    }

    @FXML
    private void voltarParaLogin(ActionEvent event) {
        try {
            // Carrega a cena de login
            Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));

            // Obtém a janela atual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Substitui a cena atual pela cena de login
            stage.setScene(new Scene(root));

            // Opcional: Ajusta o tamanho da janela
            stage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar para a página de login.");
        }
    }
}