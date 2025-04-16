package Test;

import Models.trabalhoprojeto.Cronograma;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DentroDoCronogramaController {
    @FXML private Label tituloLabel;
    @FXML private Label nomeCronogramaLabel;
    @FXML private Label dataCronogramaLabel;
    @FXML private TableView<?> atividadesTable;
    @FXML private TableColumn<?, ?> horaColumn;
    @FXML private TableColumn<?, ?> atividadeColumn;
    @FXML private TableColumn<?, ?> responsavelColumn;

    // Variável para armazenar o cronograma selecionado
    private Cronograma cronogramaSelecionado;

    // Método para inicializar os dados do cronograma
    public void initData(Cronograma cronograma) {
        this.cronogramaSelecionado = cronograma;
        atualizarUI();
    }

    private void atualizarUI() {

    }


    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LerCronogramA.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(root, 1440, 600));
            currentStage.setTitle("Detalhes do Cronograma");

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Navegação");
            alert.setHeaderText(null);
            alert.setContentText("Não foi possível voltar para a página anterior");
            alert.showAndWait();
            e.printStackTrace();
        }
    }


}