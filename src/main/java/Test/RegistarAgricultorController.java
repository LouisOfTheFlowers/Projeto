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
            showAlert("Erro", "Código postal não encontrado na base de dados.");
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
        user.setEmail(email); // Usa o email inserido pelo utilizador
        user.setTrabalhador(trabalhador);

        Email email1 = new Email();
        email1.setIdTrabalhador(trabalhador);
        email1.setEndereço(email);

        Telefone telefone1 = new Telefone();
        telefone1.setIdTrabalhador(trabalhador);
        telefone1.setNum(telefone);


        boolean sucesso = userService.registarAgricultor(user, trabalhador, agricultor, email1, telefone1);

        if (sucesso) {
            showAlert("Sucesso", "Registo de Agricultor concluído.");
            limparCampos();
        } else {
            showAlert("Erro", "Falha ao registar agricultor.");
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
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle("Selecionar Tipo de Utilizador");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
