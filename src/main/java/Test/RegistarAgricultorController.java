package Test;

import Models.trabalhoprojeto.*;
import Services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager em;

    @FXML
    void handleRegistar() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || password.isEmpty() || !password.equals(confirmPassword)) {
            showAlert("Erro", "Verifique os dados do utilizador (passwords não coincidem ou campos vazios).");
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

        String codPostal = codigoPostalField.getText().trim();
        Localidade localidade = em.find(Localidade.class, codPostal);
        if (localidade == null) {
            showAlert("Erro", "Código postal não encontrado na base de dados.");
            abrirFormularioLocalidade();
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
        user.setEmail(username + "@example.com");
        user.setTrabalhador(trabalhador);

        boolean sucesso = userService.registarAgricultor(user, trabalhador, agricultor);

        if (sucesso) {
            showAlert("Sucesso", "Registo de Agricultor concluído.");
            // Aqui podes redirecionar para o login se quiseres
        } else {
            showAlert("Erro", "Falha ao registar agricultor.");
        }
    }

    public void abrirFormularioLocalidade() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/registar_localidade.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Nova Localidade");
            stage.setScene(new Scene(root, 400, 250));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
