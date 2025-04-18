package Test;

import Models.trabalhoprojeto.*;
import Services.LocalidadeService;
import Services.UserService;
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
public class RegistarAnalistaController {

    @FXML private TextField nomeField;
    @FXML private TextField ruaField;
    @FXML private TextField portaField;
    @FXML private TextField codigoPostalField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField emailField;

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

        if (nome.isEmpty() || rua.isEmpty() || porta.isEmpty() || codigoPostal.isEmpty() ||
                username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
            showAlert("Erro", "Todos os campos são obrigatórios.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Erro", "As passwords não coincidem.");
            return;
        }

        Localidade localidade = localidadeService.findById(codigoPostal).orElse(null);
        if (localidade == null) {
            showAlert("Erro", "Código postal inválido. Adicione primeiro a localidade.");
            return;
        }

        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setNome(nome);
        trabalhador.setRua(rua);
        trabalhador.setNumeroPorta(Integer.parseInt(porta));
        trabalhador.setCodigoPostal(localidade);

        AnalistaDado analista = new AnalistaDado();
        analista.setIdTrabalhador(trabalhador);
        analista.setCodigoPostal(codigoPostal);
        analista.setNome(trabalhador.getNome());
        analista.setRua(trabalhador.getRua());
        analista.setNumeroPorta(trabalhador.getNumeroPorta());

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setTrabalhador(trabalhador);

        boolean sucesso = userService.registarAnalista(user, trabalhador, analista);

        if (sucesso) {
            showAlert("Sucesso", "Analista registado com sucesso!");
            clearForm();
        } else {
            showAlert("Erro", "Falha ao registar analista.");
        }
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
    private void voltarLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/selecionar_tipo_utilizador.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle("Selecionar Tipo de Utilizador");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar ao menu anterior.");
        }
    }
}
