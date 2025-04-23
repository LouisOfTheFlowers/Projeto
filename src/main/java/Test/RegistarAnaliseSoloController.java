package Test;

import Models.trabalhoprojeto.AnaliseSolo;
import Models.trabalhoprojeto.GestorProducao;
import Services.AnaliseSoloService;
import Services.GestorProducaoService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class RegistarAnaliseSoloController {

    @FXML private DatePicker dataPicker;
    @FXML private TextField tipoField;
    @FXML private TextField metodologiaField;
    @FXML private TextField resultadoField;
    @FXML private ComboBox<GestorProducao> gestorComboBox;

    @Autowired
    private GestorProducaoService gestorProducaoService;

    @Autowired
    private AnaliseSoloService analiseSoloService;

    @FXML
    public void initialize() {
        List<GestorProducao> gestores = gestorProducaoService.findAll();
        gestorComboBox.setItems(FXCollections.observableArrayList(gestores));
        gestorComboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(GestorProducao item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome());
            }
        });
        gestorComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(GestorProducao item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNome());
            }
        });
    }

    @FXML
    private void registarAnalise(ActionEvent event) {
        try {
            AnaliseSolo novaAnalise = new AnaliseSolo();
            novaAnalise.setData(dataPicker.getValue());
            novaAnalise.setTipoAnalise(tipoField.getText());
            novaAnalise.setMetodologia(metodologiaField.getText());
            novaAnalise.setResultadoFinal(resultadoField.getText());
            novaAnalise.setIdGestor(gestorComboBox.getValue());

            analiseSoloService.save(novaAnalise);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Análise de solo registada com sucesso!");
            alert.showAndWait();

            goBack(event);

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao registar a análise de solo.");
            alert.showAndWait();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/analise_solo.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle("Página de Análise de Solo");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao voltar para a página anterior.");
            alert.showAndWait();
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        dataPicker.setValue(null);
        tipoField.clear();
        metodologiaField.clear();
        resultadoField.clear();
        gestorComboBox.setValue(null);
    }
}
