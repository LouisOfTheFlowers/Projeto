package Test;

import Models.trabalhoprojeto.Cronograma;
import Services.CronogramaService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CronogramasAnalistaController {

    @FXML private TableView<Cronograma> tabelaCronogramas;
    @FXML private TableColumn<Cronograma, Integer> idColumn;
    @FXML private TableColumn<Cronograma, String> preparoColumn;
    @FXML private TableColumn<Cronograma, String> plantioColumn;
    @FXML private TableColumn<Cronograma, String> horticolasColumn;
    @FXML private TableColumn<Cronograma, String> gestorColumn;
    @FXML private TableColumn<Cronograma, String> dataInicioColumn;

    @Autowired
    private CronogramaService cronogramaService;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        preparoColumn.setCellValueFactory(new PropertyValueFactory<>("processoDePreparo"));
        plantioColumn.setCellValueFactory(new PropertyValueFactory<>("processoDePlantio"));
        horticolasColumn.setCellValueFactory(new PropertyValueFactory<>("tipoHorticolas"));
        gestorColumn.setCellValueFactory(cellData -> {
            var gestor = cellData.getValue().getIdGestor();
            return new SimpleStringProperty(gestor != null ? gestor.getNome() : "Desconhecido");
        });
        dataInicioColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getDtInicioPreparoTerreno() != null
                        ? cellData.getValue().getDtInicioPreparoTerreno().toString()
                        : ""
        ));

        List<Cronograma> lista = cronogramaService.findAll();
        tabelaCronogramas.setItems(FXCollections.observableArrayList(lista));
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/homepage_analista.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle("Homepage Analista");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
