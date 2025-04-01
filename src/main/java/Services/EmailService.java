package Services;

import Models.trabalhoprojeto.Email;
import Models.trabalhoprojeto.Trabalhador;
import Repositorios.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public List<Email> findAll() {
        return emailRepository.findAll();
    }

    public Optional<Email> findById(Integer id) {
        return emailRepository.findById(id);
    }

    public Email save(Email email) {
        return emailRepository.save(email);
    }

    public void deleteById(Integer id) {
        emailRepository.deleteById(id);
    }

    public void updateEmail(Integer id, String endereco, Trabalhador idTrabalhador) {
        Optional<Email> optionalEmail = emailRepository.findById(id);
        if (optionalEmail.isPresent()) {
            Email email = optionalEmail.get();
            email.setEndereço(endereco);
            email.setIdTrabalhador(idTrabalhador);
            emailRepository.save(email);
        }
    }
}