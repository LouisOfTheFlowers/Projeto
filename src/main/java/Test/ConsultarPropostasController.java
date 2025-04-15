package Test;
import Services.PropostaPlantioService;

import Models.trabalhoprojeto.PropostaPlantio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
public class ConsultarPropostasController implements Initializable {

    @Autowired
    private PropostaPlantioService propostaPlantioService;

    @FXML
    private TableView<PropostaPlantio> tablePropostas;

    @FXML
    private TableColumn<PropostaPlantio, Integer> colId;

    @FXML
    private TableColumn<PropostaPlantio, String> colHorticolas;

    @FXML
    private TableColumn<PropostaPlantio, String> colAlturaAno;

    @FXML
    private TableColumn<PropostaPlantio, String> colAgricultor;

    @FXML
    private TableColumn<PropostaPlantio, String> colGestor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar as colunas da tabela
        configureTableColumns();

        // Carregar dados ao inicializar
        carregarPropostas();
    }

    private void configureTableColumns() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colHorticolas.setCellValueFactory(new PropertyValueFactory<>("horticolas"));
        colAlturaAno.setCellValueFactory(new PropertyValueFactory<>("alturaDoAno"));

        // Para exibir o nome do agricultor e não o objeto inteiro
        colAgricultor.setCellValueFactory(cellData -> {
            String nomeAgricultor = cellData.getValue().getIdAgricultor().getNome();
            return new javafx.beans.property.SimpleStringProperty(nomeAgricultor);
        });

        // Para exibir o nome do gestor e não o objeto inteiro
        colGestor.setCellValueFactory(cellData -> {
            String nomeGestor = cellData.getValue().getIdGestor().getNome();
            return new javafx.beans.property.SimpleStringProperty(nomeGestor);
        });
    }

    @FXML
    private void consultarPropostas(ActionEvent event) {
        try {
            carregarPropostas();
            showAlert(Alert.AlertType.INFORMATION, "Consulta Realizada",
                    "As propostas de plantio foram carregadas com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro",
                    "Ocorreu um erro ao consultar as propostas de plantio: " + e.getMessage());
        }
    }

    private void carregarPropostas() {
        try {
            // Buscar todas as propostas do banco de dados
            List<PropostaPlantio> propostas = propostaPlantioService.findAll()  ;

            // Converter para ObservableList
            ObservableList<PropostaPlantio> propostasObservable = FXCollections.observableArrayList(propostas);

            // Atualizar a tabela
            tablePropostas.setItems(propostasObservable);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erro",
                    "Falha ao carregar propostas: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadScene(event, "/homepage_agricultor.fxml", "Homepage Agricultor");
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