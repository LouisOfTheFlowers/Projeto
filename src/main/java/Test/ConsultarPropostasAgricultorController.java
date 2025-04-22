package Test;

import Models.trabalhoprojeto.PropostaPlantio;
import Services.PropostaPlantioService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarPropostasAgricultorController {

    @FXML
    private ListView<String> propostasListView;

    @FXML
    private Button backButton;

    @Autowired
    private PropostaPlantioService propostaPlantioService;

    @FXML
    private void intialize() {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proposta_plantio_agricultor.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle("Gestão de Propostas");
            stage.show();
        } catch (Exception e) {
            showAlert("Erro", "Não foi possível voltar ao ecrã anterior.");
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