package Test;

import Models.trabalhoprojeto.AnaliseSolo;
import Models.trabalhoprojeto.GestorProducao;
import Models.trabalhoprojeto.Terreno;
import Services.AnaliseSoloService;
import Services.GestorProducaoService;
import Services.TerrenoService;
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
import java.util.List;

@Component
public class RegistarAnaliseSoloController {

    @FXML private DatePicker dataPicker;
    @FXML private TextField tipoField;
    @FXML private TextField metodologiaField;
    @FXML private TextField resultadoField;
    @FXML private ComboBox<GestorProducao> gestorComboBox;
    @FXML private ComboBox<Terreno> terrenoComboBox;

    @Autowired private GestorProducaoService gestorProducaoService;
    @Autowired private TerrenoService terrenoService;
    @Autowired private AnaliseSoloService analiseSoloService;

    @FXML
    public void initialize() {
        // Gestores
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

        // Terrenos
        List<Terreno> terrenos = terrenoService.findAll();
        terrenoComboBox.setItems(FXCollections.observableArrayList(terrenos));
        terrenoComboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Terreno item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : "Terreno #" + item.getId());
            }
        });
        terrenoComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Terreno item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : "Terreno #" + item.getId());
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
            novaAnalise.setIdTerreno(terrenoComboBox.getValue());

            analiseSoloService.save(novaAnalise);

            showAlert("Sucesso", "Análise de solo registada com sucesso!");
            goBack(event);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao registar a análise de solo.");
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/analise_solo.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Salvar estado atual
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Página de Análise de Solo");

            // Restaurar estado
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Erro ao voltar para a página anterior.");
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
        terrenoComboBox.setValue(null);
    }
}
