package Test;

import Models.trabalhoprojeto.AnaliseSolo;
import Services.AnaliseSoloService;
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

import java.util.List;
import java.util.Objects;

@Component
public class ConsultarAnalisesController {

    @Autowired
    private AnaliseSoloService analiseSoloService;

    @FXML
    private VBox analisesContainer;

    @FXML
    private void initialize() {
        carregarAnalises();
    }

    private void carregarAnalises() {
        List<AnaliseSolo> analises = analiseSoloService.findAllEager();
        analisesContainer.getChildren().clear();

        for (AnaliseSolo analise : analises) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-padding: 15; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 1);");
            card.setSpacing(10);

            HBox header = new HBox();
            header.setSpacing(10);
            header.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

            Label titulo = new Label("Análise #" + analise.getId());
            titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            Label status = new Label("Concluída");
            status.setStyle("-fx-text-fill: #666;");
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            header.getChildren().addAll(titulo, spacer, status);

            Button verBtn = new Button("Ver Análise");
            verBtn.setStyle("-fx-background-color: #2e8b57; -fx-text-fill: white; " +
                    "-fx-padding: 5 15; -fx-background-radius: 3;");
            verBtn.setCursor(javafx.scene.Cursor.HAND);

            verBtn.setOnAction(e -> abrirDetalhesAnalise(analise, e));

            card.getChildren().addAll(header, verBtn);
            analisesContainer.getChildren().add(card);
        }
    }

    private void abrirDetalhesAnalise(AnaliseSolo analise, ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ver_analise.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            VerAnaliseController controller = loader.getController();
            controller.setAnalise(analise);

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

            // Guarda o estado atual da janela
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            // Muda a cena
            stage.setScene(new Scene(root));
            stage.setTitle("Análise de Solo");

            // Restaura o estado anterior
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert("Erro", "Não foi possível abrir os detalhes da análise.");
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadScene(event, "/analise_solo.fxml", "Análise de Solo");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Guarda o estado da janela
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            // Muda a cena
            stage.setScene(new Scene(root));
            stage.setTitle(title);

            // Restaura o estado anterior
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

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
