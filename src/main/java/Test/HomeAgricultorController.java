package Test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeAgricultorController {

    @FXML
    private void lerCronograma(ActionEvent event) {
        // Implementar abertura da tela de cronograma
        System.out.println("Abrir tela de cronograma");
    }

    @FXML
    private void registarPropostaPlantio(ActionEvent event) {
        // Implementar abertura da tela de proposta de plantio
        System.out.println("Abrir tela de proposta de plantio");
    }

    @FXML
    private void registarDados(ActionEvent event) {
        // Implementar abertura da tela de registro de dados
        System.out.println("Abrir tela de registro de dados");
    }

    @FXML
    private void registarTerreno(ActionEvent event) {
        // Implementar abertura da tela de registro de terreno
        System.out.println("Abrir tela de registro de terreno");
    }

    @FXML
    private void consultarPropostas(ActionEvent event) {
        // Implementar abertura da tela de consulta de propostas
        System.out.println("Abrir tela de consulta de propostas");
    }
}