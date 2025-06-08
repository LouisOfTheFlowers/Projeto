package Test;

import Models.trabalhoprojeto.*;
import Services.LocalidadeService;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.Objects;

@Component
public class RegistarGestorController {

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

    @Autowired
    private LocalidadeService localidadeService;

    @FXML
    private void handleRegistar() {
        String nome = nomeField.getText();
        String rua = ruaField.getText();
        String porta = portaField.getText();
        String codigoPostal = codigoPostalField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String email = emailField.getText();
        String telefone = telefoneField.getText();

        if (nome.isEmpty() || rua.isEmpty() || porta.isEmpty() || codigoPostal.isEmpty() ||
                username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
            showAlert("Erro", "Todos os campos são obrigatórios.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Erro", "As passwords não coincidem.");
            return;
        }

        if (!isValidEmail(email)) {
            showAlert("Erro", "Email inválido.");
            return;
        }

        Localidade localidade = localidadeService.findById(codigoPostal).orElse(null);
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

        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setNome(nome);
        trabalhador.setRua(rua);
        trabalhador.setNumeroPorta(Integer.parseInt(porta));
        trabalhador.setCodigoPostal(localidade);

        GestorProducao gestor = new GestorProducao();
        gestor.setIdTrabalhador(trabalhador);
        gestor.setCodigoPostal(codigoPostal);
        gestor.setNome(nome);
        gestor.setRua(rua);
        gestor.setNumeroPorta(Integer.parseInt(porta));

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

        boolean sucesso = userService.registarGestor(user, trabalhador, gestor, email1, telefone1);

        if (sucesso) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Gestor registado com sucesso!");
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
            controller.setPaginaAnterior("/registar_gestor.fxml"); // <- aqui!

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

            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Login");

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

    private boolean isValidEmail(String email) {
        return Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$").matcher(email).matches();
    }

    private void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void clearForm() {
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

            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Selecionar Tipo de Utilizador");

            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar ao menu anterior.");
        }
    }
}
