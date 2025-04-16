package Test;

import Models.trabalhoprojeto.Cronograma;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        loadScene(event, "/proposta_plantio.fxml", "Área do Agricultor");
    }
    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(
                    getClass().getResource(fxmlPath)));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}