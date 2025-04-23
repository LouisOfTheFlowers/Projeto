package Test;

import Models.trabalhoprojeto.Relatorio;
import Services.RelatorioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class VerRelatoriosAnalistaController {

    @FXML
    private TableView<Relatorio> tabelaDados;

    @FXML
    private TableColumn<Relatorio, String> tituloColuna;

    @FXML
    private TableColumn<Relatorio, String> temaColuna;

    @FXML
    private TableColumn<Relatorio, String> tipoColuna;

    @FXML
    private TableColumn<Relatorio, String> descricaoColuna;

    @FXML
    private TableColumn<Relatorio, String> dataColuna;

    @Autowired
    private RelatorioService relatorioService;

    @FXML
    public void initialize() {
        configurarTabela();
        carregarRelatorios();
    }

    private void configurarTabela() {
        tituloColuna.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        temaColuna.setCellValueFactory(new PropertyValueFactory<>("tema"));
        tipoColuna.setCellValueFactory(new PropertyValueFactory<>("tipoRelatorio"));
        descricaoColuna.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        dataColuna.setCellValueFactory(new PropertyValueFactory<>("data"));
    }

    private void carregarRelatorios() {
        List<Relatorio> lista = relatorioService.findAll();
        ObservableList<Relatorio> observableList = FXCollections.observableArrayList(lista);
        tabelaDados.setItems(observableList);
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadScene(event, "/homepage_analista.fxml", "Homepage Analista");
    }

    private void loadScene(ActionEvent event, String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            showAlert("Erro", "Erro ao voltar à página inicial.");
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
