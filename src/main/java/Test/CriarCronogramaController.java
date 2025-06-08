package Test;

import Models.trabalhoprojeto.Cronograma;
import Models.trabalhoprojeto.GestorProducao;
import Services.CronogramaService;
import Services.GestorProducaoService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
public class CriarCronogramaController {

    @FXML private DatePicker dataPreparoPicker;
    @FXML private TextArea preparoArea;
    @FXML private TextArea plantioArea;
    @FXML private TextField horticolasField;
    @FXML private ComboBox<GestorProducao> gestorCombo;

    @Autowired
    private CronogramaService cronogramaService;

    @Autowired
    private GestorProducaoService gestorProducaoService;

    @FXML
    private void initialize() {
        List<GestorProducao> gestores = gestorProducaoService.findAll();
        gestorCombo.getItems().addAll(gestores);
    }

    @FXML
    private void guardarCronograma(ActionEvent event) {
        Cronograma cronograma = new Cronograma();
        cronograma.setDtInicioPreparoTerreno(dataPreparoPicker.getValue());
        cronograma.setProcessoDePreparo(preparoArea.getText());
        cronograma.setProcessoDePlantio(plantioArea.getText());
        cronograma.setTipoHorticolas(horticolasField.getText());
        cronograma.setIdGestor(gestorCombo.getValue());

        cronogramaService.save(cronograma);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Cronograma guardado com sucesso!");
        alert.showAndWait();

        goBack(event);
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            URL resource = getClass().getResource("/cronograma_gestor.fxml");
            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Guardar estado antes de mudar de cena
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Gestão de Cronogramas");

            // Restaurar o estado da janela
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
