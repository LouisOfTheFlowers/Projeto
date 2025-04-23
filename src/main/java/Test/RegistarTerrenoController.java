package Test;

import Models.trabalhoprojeto.Agricultor;
import Models.trabalhoprojeto.Terreno;
import Services.TerrenoService;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.List;

@Component
public class RegistarTerrenoController {

    @FXML private TextField areaField;
    @FXML private TextField coordenadasField;
    @FXML private ComboBox<Agricultor> agricultorComboBox;

    @Autowired
    private TerrenoService terrenoService;

    @Autowired
    private UserService userService;

    @FXML
    public void initialize() {
        List<Agricultor> agricultores = userService.findAllAgricultores();
        agricultorComboBox.getItems().addAll(agricultores);
    }

    @FXML
    private void registarTerreno(ActionEvent event) {

        String area = areaField.getText();
        String coordenadas = coordenadasField.getText();
        Agricultor agricultor = agricultorComboBox.getValue();

        if (area.isEmpty() || coordenadas.isEmpty() || agricultor == null) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Todos os campos devem ser preenchidos.");
            return;
        }

        Terreno terreno = new Terreno();
        terreno.setArea(area);
        terreno.setCoordenadas(coordenadas);
        terreno.setIdAgricultor(agricultor);

        terrenoService.save(terreno);
        showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Terreno registado com sucesso!");

        areaField.clear();
        coordenadasField.clear();
        agricultorComboBox.setValue(null);
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
        loadScene(event, "/terreno.fxml", "Gestão de Terrenos");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            URL resource = getClass().getResource(fxmlPath);
            Objects.requireNonNull(resource, "❌ FXML não encontrado: " + fxmlPath);

            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
