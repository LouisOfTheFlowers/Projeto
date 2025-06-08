package Test;

import Models.trabalhoprojeto.*;
import Services.TrabalhadorService;
import Services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RegistarAgricultorController {

    @FXML private TextField nomeField;
    @FXML private TextField ruaField;
    @FXML private TextField portaField;
    @FXML private TextField codigoPostalField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField emailField;
    @FXML private TextField telefoneField;

    @Autowired
    private UserService userService;
    private TrabalhadorService trabalhador;

    @PersistenceContext
    private EntityManager em;

    @FXML
    void handleRegistar() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String email = emailField.getText().trim();
        String telefone = telefoneField.getText();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || !password.equals(confirmPassword)) {
            showAlert("Erro", "Verifique os dados do utilizador (campos obrigatórios ou passwords não coincidem).");
            return;
        }

        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setNome(nomeField.getText());
        trabalhador.setRua(ruaField.getText());

        try {
            trabalhador.setNumeroPorta(Integer.parseInt(portaField.getText()));
        } catch (NumberFormatException e) {
            showAlert("Erro", "Número da porta inválido.");
            return;
        }

        String codPostal = codigoPostalField.getText();
        Localidade localidade = em.find(Localidade.class, codPostal);
        if (localidade == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Código postal inválido. Deseja adicionar uma nova localidade?");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    abrirRegistarLocalidade();
                }
            });
            return;
        }

        trabalhador.setCodigoPostal(localidade);

        Agricultor agricultor = new Agricultor();
        agricultor.setIdTrabalhador(trabalhador);
        agricultor.setNome(trabalhador.getNome());
        agricultor.setRua(trabalhador.getRua());
        agricultor.setNumeroPorta(trabalhador.getNumeroPorta());
        agricultor.setCodigoPostal(codPostal);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setTrabalhador(trabalhador);

        Email email1 = new Email();
        email1.setIdTrabalhador(trabalhador);
        email1.setEndereço(email);

        Telefone telefone1 = new Telefone();
        telefone1.setIdTrabalhador(trabalhador);
        telefone1.setNum(telefone);

        boolean sucesso = userService.registarAgricultor(user, trabalhador, agricultor, email1, telefone1);

        if (sucesso) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Agricultor registado com sucesso!");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    redirectToLogin();
                }
            });
        } else {
            showAlert("Erro", "Falha ao registar gestor.");
        }
    }

    private void abrirRegistarLocalidade() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/registar_localidade.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            // Passar o nome da página atual para o controller da localidade
            RegistarLocalidadeController controller = loader.getController();
            controller.setPaginaAnterior("/registar_agricultor.fxml"); // <- aqui!

            Stage stage = (Stage) nomeField.getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle("Registar Localidade");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível abrir a página de registo de localidade.");
        }
    }

    private void redirectToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) nomeField.getScene().getWindow();

            // Salvar estado atual
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Login");

            // Restaurar estado
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível redirecionar para a página de login.");
        }
    }

    private void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void limparCampos() {
        nomeField.clear();
        ruaField.clear();
        portaField.clear();
        codigoPostalField.clear();
        usernameField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        emailField.clear();
    }

    @FXML
    private void voltarTipoUtilizador(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/selecionar_tipo_utilizador.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Salvar estado atual
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Selecionar Tipo de Utilizador");

            // Restaurar estado
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
