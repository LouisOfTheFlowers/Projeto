package Test;

import Models.trabalhoprojeto.Cronograma;
import Services.CronogramaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class CronogramasAnalistaController {

    @FXML
    private VBox cronogramasContainer;

    @Autowired
    private CronogramaService cronogramaService;

    @FXML
    public void initialize() {
        carregarCronogramas();
    }

    private void carregarCronogramas() {
        List<Cronograma> lista = cronogramaService.findAll();

        for (Cronograma cronograma : lista) {
            VBox card = criarCartaoCronograma(cronograma);
            cronogramasContainer.getChildren().add(card);
        }
    }

    private VBox criarCartaoCronograma(Cronograma cronograma) {
        VBox card = new VBox(6);
        card.setStyle("-fx-background-color: white; -fx-padding: 12; -fx-background-radius: 8;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 1);");

        Label titulo = new Label("📅 Cronograma #" + cronograma.getId());
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label dataInicio = new Label("Início Preparo: " +
                (cronograma.getDtInicioPreparoTerreno() != null ? cronograma.getDtInicioPreparoTerreno().toString() : "N/A"));
        Label preparo = new Label("Preparo do Terreno: " + cronograma.getProcessoDePreparo());
        Label plantio = new Label("Processo de Plantio: " + cronograma.getProcessoDePlantio());
        Label horticolas = new Label("Hortícolas: " + cronograma.getTipoHorticolas());
        Label gestor = new Label("Gestor Responsável: " +
                (cronograma.getIdGestor() != null ? cronograma.getIdGestor().getNome() : "Desconhecido"));

        card.getChildren().addAll(titulo, dataInicio, preparo, plantio, horticolas, gestor);
        return card;
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/antes_cronograma_analista.fxml"));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Guardar o estado da janela
            boolean maximized = stage.isMaximized();
            double width = stage.getWidth();
            double height = stage.getHeight();

            stage.setScene(new Scene(root));
            stage.setTitle("Homepage Analista");

            // Restaurar o estado
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
}
