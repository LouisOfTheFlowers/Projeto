package Test;

import Models.trabalhoprojeto.Cronograma;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DentroDoCronogramaController {

    @FXML private Label tituloLabel;
    @FXML private Label nomeCronogramaLabel;
    @FXML private Label dataCronogramaLabel;
    @FXML private Label preparoLabel;
    @FXML private Label plantioLabel;
    @FXML private Label gestorLabel;

    private Cronograma cronogramaSelecionado;

    public void initData(Cronograma cronograma) {
        this.cronogramaSelecionado = cronograma;
        atualizarUI();
    }

    private void atualizarUI() {
        if (cronogramaSelecionado != null) {
            nomeCronogramaLabel.setText(cronogramaSelecionado.getTipoHorticolas());
            dataCronogramaLabel.setText(String.valueOf(cronogramaSelecionado.getDtInicioPreparoTerreno()));
            preparoLabel.setText(cronogramaSelecionado.getProcessoDePreparo());
            plantioLabel.setText(cronogramaSelecionado.getProcessoDePlantio());
            gestorLabel.setText(cronogramaSelecionado.getIdGestor().getNome());
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LerCronogramA.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Guardar o estado atual da janela
            boolean maximized = currentStage.isMaximized();
            double width = currentStage.getWidth();
            double height = currentStage.getHeight();

            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Ler Cronogramas");

            // Restaurar o estado da janela
            currentStage.setMaximized(maximized);
            if (!maximized) {
                currentStage.setWidth(width);
                currentStage.setHeight(height);
            }

        } catch (IOException e) {
            showAlert("Erro de Navegação", "Não foi possível voltar para a página anterior.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
