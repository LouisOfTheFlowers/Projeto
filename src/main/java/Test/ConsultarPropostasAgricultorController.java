package Test;

import Models.trabalhoprojeto.PropostaPlantio;
import Services.PropostaPlantioService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultarPropostasAgricultorController {

    @FXML
    private VBox propostasContainer;

    @Autowired
    private PropostaPlantioService propostaPlantioService;

    @FXML
    private void initialize() {
        try {
            List<PropostaPlantio> propostas = propostaPlantioService.findAll();
            propostasContainer.getChildren().clear();

            for (PropostaPlantio proposta : propostas) {
                String estado = proposta.getEstado() == null ? "Em Análise" : proposta.getEstado();

                String texto = String.format(
                        "🌱 ID: %d\nHortícolas: %s\nÉpoca: %s\nTerreno: %s\nEstado: %s",
                        proposta.getId(),
                        proposta.getHorticolas(),
                        proposta.getAlturaDoAno(),
                        proposta.getIdTerreno() != null ? proposta.getIdTerreno().getId() : "N/A",
                        estado
                );

                Label label = new Label(texto);
                label.setWrapText(true);
                label.setMaxWidth(700);
                label.setStyle("-fx-background-color: white; " +
                        "-fx-padding: 20; " +
                        "-fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 6, 0, 0, 1); " +
                        "-fx-font-size: 16px; " +
                        "-fx-text-fill: #333333;");

                propostasContainer.getChildren().add(label);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proposta_plantio_agricultor.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Guarda o estado atual da janela
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Gestão de Propostas");

            // Restaura o estado da janela
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
