package Test;

import Models.trabalhoprojeto.*;
import Services.PropostaPlantioService;
import Services.TerrenoService;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Component
public class INRegistarPropostaPlantioController {

    @FXML private TextField horticolasField;
    @FXML private TextField alturaDoAnoField;
    @FXML private ComboBox<Terreno> terrenoComboBox;
    @FXML private ComboBox<Agricultor> agricultorComboBox;
    @FXML private ComboBox<GestorProducao> gestorComboBox;
    @FXML private Button registarButton;

    @Autowired
    private PropostaPlantioService propostaPlantioService;

    @Autowired
    private TerrenoService terrenoService;

    @Autowired
    private UserService userService;

    @FXML
    public void initialize() {
        // Terrenos
        List<Terreno> terrenos = terrenoService.findAll();
        terrenoComboBox.getItems().addAll(terrenos);
        terrenoComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Terreno terreno) {
                return terreno == null ? "" : "Terreno #" + terreno.getId();
            }

            @Override
            public Terreno fromString(String s) {
                return null;
            }
        });

        // Agricultores
        List<Agricultor> agricultores = userService.findAllAgricultores();
        agricultorComboBox.getItems().addAll(agricultores);
        agricultorComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Agricultor agricultor) {
                return agricultor == null ? "" : agricultor.getNome();
            }

            @Override
            public Agricultor fromString(String s) {
                return null;
            }
        });

        // Gestores
        List<GestorProducao> gestores = userService.findAllGestores();
        gestorComboBox.getItems().addAll(gestores);
        gestorComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(GestorProducao gestor) {
                return gestor == null ? "" : gestor.getNome();
            }

            @Override
            public GestorProducao fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    private void handleRegistar() {
        String horticolas = horticolasField.getText();
        String alturaDoAno = alturaDoAnoField.getText();
        Terreno terreno = terrenoComboBox.getValue();
        Agricultor agricultor = agricultorComboBox.getValue();
        GestorProducao gestor = gestorComboBox.getValue();

        if (horticolas.isEmpty() || alturaDoAno.isEmpty() || terreno == null || agricultor == null || gestor == null) {
            showAlert("Erro", "Todos os campos devem ser preenchidos.");
            return;
        }

        PropostaPlantio proposta = new PropostaPlantio();
        proposta.setHorticolas(horticolas);
        proposta.setAlturaDoAno(alturaDoAno);
        proposta.setIdTerreno(terreno);
        proposta.setIdAgricultor(agricultor);
        proposta.setIdGestor(gestor);

        propostaPlantioService.save(proposta);
        showAlertComRedirect("Sucesso", "Proposta de Plantio registada com sucesso!");
    }

    private void showAlertComRedirect(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        alert.setOnHidden(event -> {
            // Redirecionar para a página anterior após o utilizador clicar em "OK"
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/proposta_plantio_agricultor.fxml"));
                loader.setControllerFactory(AppContextProvider.getApplicationContext()::getBean);
                Parent root = loader.load();

                Stage stage = (Stage) registarButton.getScene().getWindow();
                stage.setScene(new Scene(root, 1440, 600));
                stage.setTitle("Propostas de Plantio");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        alert.showAndWait();
    }


    private void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
    @FXML
    private void goBack(ActionEvent event) {
        loadScene(event, "/proposta_plantio_agricultor.fxml", "Propostas de Plantio");
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
