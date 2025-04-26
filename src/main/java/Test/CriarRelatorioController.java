package Test;

import Models.trabalhoprojeto.AnalistaDado;
import Models.trabalhoprojeto.Relatorio;
import Services.RelatorioService;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
public class CriarRelatorioController {

    @FXML private TextField tituloField;
    @FXML private TextField temaField;
    @FXML private TextField tipoField;
    @FXML private TextArea descricaoArea;
    @FXML private ComboBox<AnalistaDado> analistaComboBox;

    @Autowired private RelatorioService relatorioService;
    @Autowired private UserService userService;

    @FXML
    public void initialize() {
        List<AnalistaDado> analistas = userService.findAllAnalistas();
        analistaComboBox.getItems().addAll(analistas);
        analistaComboBox.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(AnalistaDado analista) {
                return analista != null ? analista.getNome() : "";
            }
            @Override
            public AnalistaDado fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    private void handleGuardar() {
        String titulo = tituloField.getText();
        String tema = temaField.getText();
        String tipo = tipoField.getText();
        String descricao = descricaoArea.getText();
        AnalistaDado analista = analistaComboBox.getValue();

        if (titulo.isEmpty() || tema.isEmpty() || tipo.isEmpty() || descricao.isEmpty() || analista == null) {
            showAlert("Erro", "Todos os campos devem ser preenchidos.");
            return;
        }

        Relatorio relatorio = new Relatorio();
        relatorio.setTitulo(titulo);
        relatorio.setTema(tema);
        relatorio.setTipoRelatorio(tipo);
        relatorio.setDescricao(descricao);
        relatorio.setData(LocalDate.now());
        relatorio.setIdAnalista(analista);

        relatorioService.save(relatorio);
        showAlert("Sucesso", "Relatório criado com sucesso!");
        clearFields();
    }

    private void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void clearFields() {
        tituloField.clear();
        temaField.clear();
        tipoField.clear();
        descricaoArea.clear();
        analistaComboBox.setValue(null);
    }

    @FXML
    private void goBack(ActionEvent event) {

        loadScene(event, "/antes_relatorio_analista.fxml", "Ações Relatorios");
    }
    private void loadScene(ActionEvent event, String fxmlPath, String title) {
        try {
            URL resource = getClass().getResource(fxmlPath);
            Objects.requireNonNull(resource, "❌ FXML não encontrado: " + fxmlPath);

            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1440, 600));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
