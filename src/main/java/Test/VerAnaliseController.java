package Test;

import Models.trabalhoprojeto.AnaliseSolo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;

@Component
public class VerAnaliseController {

    @FXML private Button backButton;
    @FXML private Label dataLabel;
    @FXML private Label tipoAnaliseLabel;
    @FXML private Label metodologiaLabel;
    @FXML private Label resultadoFinalLabel;
    @FXML private Label gestorLabel;

    private AnaliseSolo analise;

    public void setAnalise(AnaliseSolo analise) {
        this.analise = analise;
        preencherDados();
    }

    private void preencherDados() {
        if (analise != null) {
            dataLabel.setText("Data da Análise: " + analise.getData());
            tipoAnaliseLabel.setText("Tipo de Análise: " + analise.getTipoAnalise());
            metodologiaLabel.setText("Metodologia: " + analise.getMetodologia());
            resultadoFinalLabel.setText("Resultado Final: " + analise.getResultadoFinal());
            gestorLabel.setText("Gestor Responsável: " + analise.getIdGestor().getNome());
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadScene(event, "/consultar_analises.fxml", "Análises de Solo Submetidas");
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
            showAlert("Erro", "Não foi possível carregar a página.");
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
