package Test;

import Models.trabalhoprojeto.Localidade;
import Services.LocalidadeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RegistarLocalidadeController {

    @FXML
    private TextField codigoPostalField;

    @FXML
    private TextField distritoField;

    @FXML
    private TextField localidadeField;

    @Autowired
    private LocalidadeService localidadeService;

    private String paginaAnterior;


    public void setPaginaAnterior(String paginaAnterior) {
        this.paginaAnterior = paginaAnterior;
    }

    @FXML
    private void handleRegistarLocalidade() {
        String codigoPostal = codigoPostalField.getText();
        String nome = distritoField.getText();
        String localidade_distrito = localidadeField.getText();

        if (codigoPostal.isEmpty() || nome.isEmpty() || localidade_distrito.isEmpty()) {
            showAlert("Erro", "Todos os campos são obrigatórios.");
            return;
        }

        Localidade localidade = new Localidade();
        localidade.setCodigoPostal(codigoPostal);
        localidade.setDistrito(nome);
        localidade.setLocalidade(localidade_distrito);

        localidadeService.save(localidade);

        showAlert("Sucesso", "Localidade registada com sucesso!");

        voltarParaPaginaAnterior();
    }

    @FXML
    private void voltarParaPaginaAnterior() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(paginaAnterior));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) codigoPostalField.getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle("Registo de Utilizador");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar para a página anterior.");
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
