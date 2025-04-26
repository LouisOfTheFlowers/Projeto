package Test;

import Models.trabalhoprojeto.Relatorio;
import Services.RelatorioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class VerRelatoriosAnalistaController {

    @FXML
    private VBox relatoriosContainer;

    @Autowired
    private RelatorioService relatorioService;

    @FXML
    public void initialize() {
        carregarRelatorios();
    }

    private void carregarRelatorios() {
        List<Relatorio> lista = relatorioService.findAll();

        for (Relatorio relatorio : lista) {
            VBox card = criarCartaoRelatorio(relatorio);
            relatoriosContainer.getChildren().add(card);
        }
    }

    private VBox criarCartaoRelatorio(Relatorio relatorio) {
        VBox card = new VBox(6);
        card.setStyle("-fx-background-color: white; -fx-padding: 12; -fx-background-radius: 8;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 1);");

        Label titulo = new Label("📄 " + relatorio.getTitulo());
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label tema = new Label("Tema: " + relatorio.getTema());
        Label tipo = new Label("Tipo: " + relatorio.getTipoRelatorio());
        Label data = new Label("Data: " + relatorio.getData());
        Text descricao = new Text("Descrição: " + relatorio.getDescricao());
        descricao.setWrappingWidth(700); // ajusta conforme necessário

        card.getChildren().addAll(titulo, tema, tipo, data, descricao);
        return card;
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadScene(event, "/antes_relatorio_analista.fxml", "Homepage Analista");
    }

    private void loadScene(ActionEvent event, String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            showAlert("Erro", "Erro ao voltar à página inicial.");
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
