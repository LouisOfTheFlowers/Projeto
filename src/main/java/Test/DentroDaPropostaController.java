package Test;

import Models.trabalhoprojeto.PropostaPlantio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class DentroDaPropostaController {
    @FXML private Label tituloLabel;
    @FXML private Label tituloPropostaLabe;
    @FXML private Label dataPropostaLabe;
    @FXML private Label estadoPropostaLabe;
    @FXML private Label proponentePropostaLabe;
    @FXML private TextArea descricaoPropostaText;
    @FXML private TableView<?> plantasTable;
    @FXML private TableColumn<?, ?> nomePlantaColumn;
    @FXML private TableColumn<?, ?> quantidadeColumn;
    @FXML private TableColumn<?, ?> especieColumn;
    @FXML private TableColumn<?, ?> observacoesColumn;

    // Variável para armazenar a proposta selecionada
    private PropostaPlantio propostaSelecionada;
    private Stage previousStage;
    private int propostaId;

    // Método para inicializar os dados da proposta
    public void initData(PropostaPlantio proposta) {
        this.propostaSelecionada = proposta;
        atualizarUI();
    }

    public void setPropostaId(int propostaId) {
        this.propostaId = propostaId;
    }

    // Método para definir o stage anterior
    public void setPreviousStage(Stage stage) {
        this.previousStage = stage;
    }

    private void atualizarUI() {

    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ConsultarPropostas.fxml"));
            Parent root = loader.load();

            // Obtém o stage atual
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Define a cena anterior no mesmo stage
            currentStage.setScene(new Scene(root));

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível voltar à página anterior");
            e.printStackTrace();
        }
    }

    private void mostrarMensagem(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarErroNavegacao() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de Navegação");
        alert.setHeaderText(null);
        alert.setContentText("Não foi possível voltar para a página anterior");
        alert.showAndWait();
    }
}