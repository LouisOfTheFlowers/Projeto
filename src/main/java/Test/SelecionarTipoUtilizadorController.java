package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class SelecionarTipoUtilizadorController {

    @FXML
    void handleSelecionarAgricultor(ActionEvent event) {
        loadScene(event, "registar_agricultor.fxml", "Registar Agricultor");
    }

    @FXML
    void handleSelecionarGestor(ActionEvent event) {
        loadScene(event, "registar_gestor.fxml", "Registar Gestor de Produção");
    }

    @FXML
    void handleSelecionarAnalista(ActionEvent event) {
        loadScene(event, "registar_analista.fxml", "Registar Analista de Dados");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/" + fxmlPath)));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle(title);

            // Restaurar maximizado ou tamanho anterior
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void voltarLogin(ActionEvent event) {
        loadScene(event, "login.fxml", "Login");
    }
}
