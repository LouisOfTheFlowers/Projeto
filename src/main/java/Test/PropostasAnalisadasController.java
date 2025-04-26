package Test;

import Models.trabalhoprojeto.PropostaPlantio;
import Services.PropostaPlantioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class PropostasAnalisadasController {

    @FXML private VBox propostasAnalisadasContainer;
    @FXML private Button backButton;

    @Autowired
    private PropostaPlantioService propostaService;

    @FXML
    private void initialize() {
        carregarPropostasAnalisadas();
    }

    private void carregarPropostasAnalisadas() {
        List<PropostaPlantio> propostas = propostaService.findAnalisadas();
        propostasAnalisadasContainer.getChildren().clear();

        for (PropostaPlantio p : propostas) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-padding: 15;" +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 1);");
            card.setSpacing(10);

            HBox header = new HBox(10);
            header.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

            Label titulo = new Label("Proposta #" + p.getId());
            titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            Label status = new Label(p.getEstado());
            status.setStyle("-fx-text-fill: #666;");
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            header.getChildren().addAll(titulo, spacer, status);

            Button verBtn = new Button("Ver Proposta");
            verBtn.setStyle("-fx-background-color: #2e8b57; -fx-text-fill: white;" +
                    "-fx-padding: 5 15; -fx-background-radius: 3;");
            verBtn.setCursor(javafx.scene.Cursor.HAND);
            verBtn.setOnAction(e -> abrirDetalhesProposta(p, e));

            card.getChildren().addAll(header, verBtn);
            propostasAnalisadasContainer.getChildren().add(card);
        }
    }

    private void abrirDetalhesProposta(PropostaPlantio proposta, ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ver_proposta_analisada.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            VerPropostaAnalisadaController controller = loader.getController();
            controller.setProposta(proposta);

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle("Detalhes da Proposta");
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert("Erro", "Não foi possível abrir os detalhes da proposta.");
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadScene(event, "/proposta_plantio_gestor.fxml", "Propostas de Plantio");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxmlPath)));
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
