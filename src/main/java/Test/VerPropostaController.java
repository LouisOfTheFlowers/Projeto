package Test;

import Models.trabalhoprojeto.PropostaPlantio;
import Services.PropostaPlantioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerPropostaController {

    @FXML private Label estadoLabel;
    @FXML private Label nomeCulturaLabel;
    @FXML private Label dataPropostaLabel;
    @FXML private Label areaLabel;
    @FXML private Label descricaoLabel;
    @FXML private Button backButton;

    @Autowired
    private PropostaPlantioService propostaService;

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
    private void aprovarProposta(ActionEvent event) {
        if (propostaAtual != null) {
            propostaAtual.setEstado("Aprovada");
            propostaService.save(propostaAtual);
            estadoLabel.setText("Estado: Aprovada");
            showAlertAndGoBack("Proposta Aprovada", "A proposta foi aprovada com sucesso.", event);
        }
    }

    @FXML
    private void recusarProposta(ActionEvent event) {
        if (propostaAtual != null) {
            propostaAtual.setEstado("Recusada");
            propostaService.save(propostaAtual);
            estadoLabel.setText("Estado: Recusada");
            showAlertAndGoBack("Proposta Recusada", "A proposta foi recusada.", event);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/consultar_propostas.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Propostas Submetidas");

            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar à página anterior.");
        }
    }

    private void showAlertAndGoBack(String title, String msg, ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
        goBack(event);
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
