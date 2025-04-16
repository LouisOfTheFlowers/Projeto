package Test;

import Models.trabalhoprojeto.Cronograma;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;

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
    private void voltar(ActionEvent event) {
        // Fecha a janela atual
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }


}