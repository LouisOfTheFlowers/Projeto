package Test;

import Models.trabalhoprojeto.PropostaPlantio;
import Services.PropostaPlantioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ConsultarPropostasGestorController {

    @Autowired
    private PropostaPlantioService propostaService;

    @FXML
    private VBox propostasContainer;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        carregarPropostas();
    }

    private void carregarPropostas() {
        List<PropostaPlantio> propostas = propostaService.findAll()
                .stream()
                .filter(p -> p.getEstado() == null || p.getEstado().equalsIgnoreCase("Em Análise"))
                .toList();

        propostasContainer.getChildren().clear();

        for (PropostaPlantio proposta : propostas) {
            VBox card = new VBox();
            card.setSpacing(10);
            card.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-padding: 15;" +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 1);");

            HBox header = new HBox(10);
            header.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

            Label titulo = new Label("Proposta #" + proposta.getId());
            titulo.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

            Label estado = new Label(proposta.getEstado() == null ? "Em Análise" : proposta.getEstado());
            estado.setStyle("-fx-text-fill: #666;");
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            header.getChildren().addAll(titulo, spacer, estado);

            String detalhes = String.format(
                    "Hortícolas: %s\nÉpoca: %s\nTerreno: %s",
                    proposta.getHorticolas(),
                    proposta.getAlturaDoAno(),
                    proposta.getIdTerreno() != null ? proposta.getIdTerreno().getId() : "N/A"
            );
            Label detalhesLabel = new Label(detalhes);
            detalhesLabel.setStyle("-fx-text-fill: #333; -fx-font-size: 14px;");

            Button verBtn = new Button("Ver Proposta");
            verBtn.setStyle("-fx-background-color: #2e8b57; -fx-text-fill: white; -fx-padding: 5 15; -fx-background-radius: 3;");
            verBtn.setOnAction(e -> abrirDetalhesProposta(proposta, e));

            card.getChildren().addAll(header, detalhesLabel, verBtn);
            propostasContainer.getChildren().add(card);
        }
    }

    private void abrirDetalhesProposta(PropostaPlantio proposta, ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ver_proposta.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            VerPropostaController controller = loader.getController();
            controller.setProposta(proposta);

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

            // Guardar estado da janela antes
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Detalhes da Proposta");

            // Restaurar estado
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert("Erro", "Não foi possível abrir os detalhes da proposta.");
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/proposta_plantio_gestor.fxml")));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Guardar estado da janela
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Área do Gestor de Produção");

            // Restaurar estado
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar.");
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
