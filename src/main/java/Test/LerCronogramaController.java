package Test;

import Models.trabalhoprojeto.Cronograma;
import Services.CronogramaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Component
public class LerCronogramaController {

    @Autowired
    private CronogramaService cronogramaService;

    @FXML
    private VBox cronogramasContainer;

    @FXML
    private void initialize() {
        List<Cronograma> lista = cronogramaService.findAll();
        cronogramasContainer.getChildren().clear();

        for (Cronograma c : lista) {
            VBox card = new VBox(10);
            card.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-padding: 15;" +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 4, 0, 0, 1);");

            Label titulo = new Label("📅 Início: " + c.getDtInicioPreparoTerreno());
            titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

            Label detalhes = new Label("Hortícolas: " + c.getTipoHorticolas() +
                    "\nPreparo: " + c.getProcessoDePreparo() +
                    "\nPlantio: " + c.getProcessoDePlantio() +
                    "\nGestor: " + c.getIdGestor().getNome());
            detalhes.setWrapText(true);

            Button verBtn = new Button("Ler Cronograma");
            verBtn.setStyle("-fx-background-color: #2e8b57; -fx-text-fill: white; -fx-background-radius: 5; -fx-padding: 5 15;");
            verBtn.setCursor(javafx.scene.Cursor.HAND);

            verBtn.setOnAction(e -> abrirDetalhesCronograma(c, e));

            card.getChildren().addAll(titulo, detalhes, verBtn);
            cronogramasContainer.getChildren().add(card);
        }
    }

    private void abrirDetalhesCronograma(Cronograma cronograma, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dentro_do_cronograma.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Test.DentroDoCronogramaController controller = loader.getController();
            controller.initData(cronograma);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Guardar estado da janela
            boolean maximized = currentStage.isMaximized();
            double width = currentStage.getWidth();
            double height = currentStage.getHeight();

            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Detalhes do Cronograma");

            // Restaurar o estado
            currentStage.setMaximized(maximized);
            if (!maximized) {
                currentStage.setWidth(width);
                currentStage.setHeight(height);
            }

            currentStage.show();

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível abrir os detalhes do cronograma");
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadScene(event, "/acoes_cronogramas.fxml", "Ações de Cronograma");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            URL resource = getClass().getResource(fxmlPath);
            Objects.requireNonNull(resource, "❌ FXML não encontrado: " + fxmlPath);

            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Guardar estado da janela
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle(title);

            // Restaurar o estado
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

            stage.show();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Não foi possível carregar a página.");
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
