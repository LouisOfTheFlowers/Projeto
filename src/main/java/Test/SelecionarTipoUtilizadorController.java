package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class SelecionarTipoUtilizadorController {

    @FXML
    void handleSelecionarAgricultor(ActionEvent event) throws IOException {
        loadScene(event,"registar_agricultor.fxml", "Registar Agricultor");
    }

    @FXML
    void handleSelecionarGestor(ActionEvent event) throws IOException {
        loadScene(event,"registar_gestor.fxml", "Registar Gestor de Produção");
    }

    @FXML
    void handleSelecionarAnalista(ActionEvent event) throws IOException {
        loadScene(event,"registar_analista.fxml", "Registar Analista de Dados");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/" + fxmlPath)));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}