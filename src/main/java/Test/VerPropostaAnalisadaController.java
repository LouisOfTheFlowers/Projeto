package Test;

import Models.trabalhoprojeto.PropostaPlantio;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class VerPropostaAnalisadaController {

    @FXML private Label estadoLabel;
    @FXML private Label nomeCulturaLabel;
    @FXML private Label dataPropostaLabel;
    @FXML private Label areaLabel;
    @FXML private Label descricaoLabel;
    @FXML private Button backButton;

    private PropostaPlantio propostaAtual;

    public void setProposta(PropostaPlantio proposta) {
        this.propostaAtual = proposta;
        estadoLabel.setText("Estado: " + (proposta.getEstado() == null ? "Em Análise" : proposta.getEstado()));
        nomeCulturaLabel.setText("Nome da Cultura: " + proposta.getHorticolas());
        dataPropostaLabel.setText("Altura do Ano: " + proposta.getAlturaDoAno());
        areaLabel.setText("Terreno ID: " + proposta.getIdTerreno().getId());
        descricaoLabel.setText("Gestor ID: " + proposta.getIdGestor().getId());
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/propostas_analisadas.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle("Propostas Analisadas");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar à página anterior.");
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
