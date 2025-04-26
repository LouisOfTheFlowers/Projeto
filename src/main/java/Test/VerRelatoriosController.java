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
        List<Relatorio> relatorios = relatorioService.findAll(); // ou método equivalente no teu service
        relatoriosContainer.getChildren().clear();

        for (Relatorio r : relatorios) {
            String info = "📝 " + r.getTitulo() + " (" + r.getData() + ")\n"
                    + "Tema: " + r.getTema() + "\n"
                    + "Tipo: " + r.getTipoRelatorio() + "\n"
                    + "Descrição: " + r.getDescricao();

            Label label = new Label(info);
            label.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-background-radius: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 1);");
            relatoriosContainer.getChildren().add(label);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            URL resource = getClass().getResource("/relatorio_gestor.fxml");
            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle("Relatórios");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
