package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Component
public class RelatoriosAnalistaController {

    @FXML
    private void exportarRelatorio(ActionEvent event) {
        showAlert("Registar relatório", "Funcionalidade de exportação ainda não implementada.");
    }

    @FXML
    private void goBack(ActionEvent event) {
        carregarPagina(event, "/antes_relatorio_analista.fxml", "Ações Relatórios");
    }

    private void carregarPagina(ActionEvent event, String fxml, String titulo) {
        try {
            URL resource = getClass().getResource(fxml);
            Objects.requireNonNull(resource, "❌ FXML não encontrado: " + fxml);

            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle(titulo);

            // Restaurar maximizado ou tamanho anterior
            stage.setMaximized(maximized);
            if (!maximized) {
                stage.setWidth(width);
                stage.setHeight(height);
            }

            stage.show();
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a página.");
            e.printStackTrace();
        }
    }

    private void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
