package Test;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;

@Component
public class VerCronogramasController {

    @FXML private VBox cronogramasContainer;

    @FXML
    private void initialize() {
        // Simulação: depois ligas à base de dados
        for (int i = 1; i <= 3; i++) {
            Label cronograma = new Label("Cronograma #" + i + ": Descrição de exemplo");
            cronogramasContainer.getChildren().add(cronograma);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            URL resource = getClass().getResource("/cronograma_gestor.fxml");
            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle("Gestão de Cronogramas");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
