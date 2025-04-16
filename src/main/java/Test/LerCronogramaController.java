package Test;

import Models.trabalhoprojeto.Cronograma;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LerCronogramaController {

    @FXML
    private void lerCronograma(ActionEvent event) {
        try {
            // Carrega o arquivo FXML da página de detalhes
            FXMLLoader loader = new FXMLLoader();
            // Certifique-se que o caminho está correto - ajuste conforme sua estrutura de pacotes
            loader.setLocation(getClass().getResource("/Dentro_do_cronograma.fxml"));

            Parent root = loader.load();

            // Obtém o controller da nova cena
            DentroDoCronogramaController controller = loader.getController();

            // Configura a cena
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Detalhes do Cronograma");

            // Fecha a janela atual (opcional)
            // ((Node)(event.getSource())).getScene().getWindow().hide();

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Mostra mensagem de erro para o usuário
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Não foi possível abrir o cronograma");
            alert.setContentText("O arquivo de visualização não foi encontrado.");
            alert.showAndWait();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Alterado para voltar para acoes_cronogramas em vez da homepage
        loadScene(event, "/acoes_cronogramas.fxml", "Ações de Cronograma");
    }

    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(
                    getClass().getResource(fxmlPath)));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erro de Navegação",
                    "Não foi possível carregar a página: " + title);
            e.printStackTrace();
        }
    }
}