package Test;

import jakarta.persistence.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Models.trabalhoprojeto.Trabalhador;
import Models.trabalhoprojeto.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Component
public class LoginController {

    @FXML private ImageView loginImage;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private ProgressIndicator loadingIndicator;

    @PersistenceContext
    private EntityManager em;

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
            String role = authenticate(username, password);

            Platform.runLater(() -> {
                loadingIndicator.setVisible(false);
                loginButton.setDisable(false);

                if (role != null) {
                    loginButton.setStyle("-fx-background-color: #90ee90;");

                    switch (role.toLowerCase()) {
                        case "agricultor" -> loadScene(event, "/homepage_agricultor.fxml", "Agricultor");
                        case "gestor" -> loadScene(event, "/homepage_gestor.fxml", "Gestor de Produção");
                        case "analista" -> loadScene(event, "/homepage_analista.fxml", "Analista de Dados");
                        default -> showAlert("Login Falhou", "Tipo de utilizador desconhecido.");
                    }
                } else {
                    loginButton.setStyle("-fx-background-color: #ff6b6b;");
                    showAlert("Login Falhou", "Credenciais inválidas.");
                }
            });
        }).start();
    }

    private String authenticate(String username, String password) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);

            User user = query.getSingleResult();

            if (user != null && user.getPassword().equals(password)) {
                Trabalhador trabalhador = user.getTrabalhador();

                if (trabalhador != null) {
                    if (trabalhador.getAgricultores() != null && !trabalhador.getAgricultores().isEmpty()) {
                        return "Agricultor";
                    }
                    if (trabalhador.getGestoresProducao() != null && !trabalhador.getGestoresProducao().isEmpty()) {
                        return "Gestor";
                    }
                    if (trabalhador.getAnalistaDados() != null && !trabalhador.getAnalistaDados().isEmpty()) {
                        return "Analista";
                    }
                }
            }
        } catch (NoResultException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
            System.out.println("🔎 Tentando carregar: " + fxmlPath);
            System.out.println("➡️  URL encontrado: " + resource);

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
