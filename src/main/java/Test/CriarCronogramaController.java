package Test;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;

@Component
public class CriarCronogramaController {

    @FXML private TextField nomeField;
    @FXML private TextArea descricaoArea;
    @FXML private Button backButton;

    @FXML
    private void guardarCronograma(ActionEvent event) {
        String nome = nomeField.getText();
        String descricao = descricaoArea.getText();

        // Aqui adicionarias lógica para guardar na BD
        System.out.println("Criar cronograma: " + nome + " - " + descricao);

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
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle("Gestão de Cronogramas");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
