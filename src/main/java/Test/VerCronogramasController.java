package Test;

import Models.trabalhoprojeto.Cronograma;
import Services.CronogramaService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.Objects;

@Component
public class VerCronogramasController {

    @FXML private VBox cronogramasContainer;

    @Autowired
    private CronogramaService cronogramaService;

    @FXML
    private void initialize() {
        System.out.println("🔧 Método initialize() chamado");

        try {
            List<Cronograma> lista = cronogramaService.findAll();
            System.out.println("✅ Total de cronogramas encontrados: " + lista.size());

            cronogramasContainer.getChildren().clear();

            for (Cronograma c : lista) {
                if (c != null) {
                    String texto = "📅 Início: " + c.getDtInicioPreparoTerreno()
                            + "\nHortícolas: " + c.getTipoHorticolas()
                            + "\nPreparo: " + c.getProcessoDePreparo()
                            + "\nPlantio: " + c.getProcessoDePlantio()
                            + "\nGestor: " + (c.getIdGestor() != null ? c.getIdGestor().getNome() : "Desconhecido");

                    Label label = new Label(texto);
                    label.setWrapText(true);
                    label.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-background-radius: 5; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 1);");

                    cronogramasContainer.getChildren().add(label);
                } else {
                    System.out.println("⚠️ Cronograma nulo encontrado na lista.");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao carregar cronogramas: " + e.getMessage());
            e.printStackTrace();
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

            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Gestão de Cronogramas");

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
