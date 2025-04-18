package Test;

import Models.trabalhoprojeto.Localidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class RegistarLocalidadeController {

    @FXML
    private TextField codigoPostalField;

    @FXML
    private TextField localidadeField;

    @FXML
    private TextField distritoField;

    @FXML
    private javafx.scene.control.Button btnGuardar;

    @PersistenceContext
    private EntityManager em;

    @FXML
    public void initialize() {
        btnGuardar.setOnAction(e -> handleGuardar());
    }

    @FXML
    @Transactional
    public void handleGuardar() {
        String codigoPostal = codigoPostalField.getText().trim();
        String localidade = localidadeField.getText().trim();
        String distrito = distritoField.getText().trim();

        if (codigoPostal.isEmpty() || localidade.isEmpty() || distrito.isEmpty()) {
            showAlert("Erro", "Todos os campos são obrigatórios.");
            return;
        }

        try {
            Localidade nova = new Localidade();
            nova.setCodigoPostal(codigoPostal);
            nova.setLocalidade(localidade);
            nova.setDistrito(distrito);

            em.persist(nova);

            showAlert("Sucesso", "Localidade registada com sucesso.");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao guardar localidade.");
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
