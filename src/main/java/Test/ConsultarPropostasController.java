package Test;

import Models.trabalhoprojeto.PropostaPlantio;
import Services.PropostaPlantioService;
import jakarta.annotation.PostConstruct;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ConsultarPropostasController {

    @FXML
    private ListView<String> propostasListView;

    @FXML
    private Button backButton;

    @Autowired
    private PropostaPlantioService propostaPlantioService;

    @FXML
    public void initialize() {
        try {
            List<PropostaPlantio> propostas = propostaPlantioService.findAll();
            for (PropostaPlantio proposta : propostas) {
                String texto = String.format(
                        "ID: %d | Hortícolas: %s | Época: %s | Terreno: %d",
                        proposta.getId(),
                        proposta.getHorticolas(),
                        proposta.getAlturaDoAno(),
                        proposta.getIdTerreno() != null ? proposta.getIdTerreno().getId() : null
                );
                propostasListView.getItems().add(texto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível carregar as propostas.");
        }
    }


    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proposta_plantio.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle("Gestão de Propostas");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar ao ecrã anterior.");
        }
    }

    private void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
