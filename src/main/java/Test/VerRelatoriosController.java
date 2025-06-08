package Test;

import Models.trabalhoprojeto.Relatorio;
import Services.RelatorioService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.Objects;

@Component
public class VerRelatoriosController {

    @FXML private VBox relatoriosContainer;

    @Autowired
    private RelatorioService relatorioService;

    @FXML
    private void initialize() {
        List<Relatorio> relatorios = relatorioService.findAll();
        relatoriosContainer.getChildren().clear();

        for (Relatorio r : relatorios) {
            String info = "📝 " + r.getTitulo() + " (" + r.getData() + ")\n"
                    + "Tema: " + r.getTema() + "\n"
                    + "Tipo: " + r.getTipoRelatorio() + "\n"
                    + "Descrição: " + r.getDescricao();

            Label label = new Label(info);
            label.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-background-radius: 5; "
                    + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 1);");
            label.setWrapText(true);
            relatoriosContainer.getChildren().add(label);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            URL resource = getClass().getResource("/relatorio_gestor.fxml");
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(resource));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Relatórios");

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
