package Test;

import Models.trabalhoprojeto.User;
import Services.UserService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;

@Component
public class LoginController {

    @FXML private ImageView loginImage;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private ProgressIndicator loadingIndicator;

    @Autowired
    private UserService userService;

    @FXML
    public void initialize() {
        try {
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/novaImagem.jpg")));
            loginImage.setImage(image);
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem de login: " + e.getMessage());
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Login Falhou", "Por favor, preencha os campos.");
            return;
        }

        loadingIndicator.setVisible(true);
        loginButton.setDisable(true);

        new Thread(() -> {
            try {
                String role = authenticate(username, password);

                Platform.runLater(() -> {
                    loadingIndicator.setVisible(false);
                    loginButton.setDisable(false);

                    if (role != null) {
                        loginButton.setStyle("-fx-background-color: #90ee90;");

                        switch (role.toLowerCase()) {
                            case "agricultor" -> loadScene(event, "/homepage_agricultor.fxml", "Agricultor");
                            case "gestor"     -> loadScene(event, "/homepage_gestor.fxml", "Gestor de Produção");
                            case "analista"   -> loadScene(event, "/homepage_analista.fxml", "Analista de Dados");
                            default           -> showAlert("Login Falhou", "Tipo de utilizador desconhecido.");
                        }
                    } else {
                        loginButton.setStyle("-fx-background-color: #ff6b6b;");
                        showAlert("Login Falhou", "Credenciais inválidas.");
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> {
                    loadingIndicator.setVisible(false);
                    loginButton.setDisable(false);
                    showAlert("Erro", "Ocorreu um erro durante o login.");
                });
            }
        }).start();
    }

    private String authenticate(String username, String password) {
        try {
            return userService.autenticarComRole(username, password);
        } catch (Exception e) {
            System.err.println("Erro na autenticação: " + e.getMessage());
            return null;
        }
    }

    @FXML
    private void handleForgotPassword(ActionEvent event) {
        loadScene(event, "/esqueceu_password.fxml", "Recuperar Password");
    }

    @FXML
    private void handleRegistarLink(ActionEvent event) {
        loadScene(event, "/selecionar_tipo_utilizador.fxml", "Criar Conta");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            URL resource = getClass().getResource(fxmlPath);
            Objects.requireNonNull(resource, "❌ FXML não encontrado: " + fxmlPath);

            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            showAlert("Erro", "Não foi possível carregar o ecrã: " + title);
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
