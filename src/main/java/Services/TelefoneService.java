package Services;

import Models.trabalhoprojeto.Telefone;
import Repositorios.TelefoneRepository;
import Models.trabalhoprojeto.Trabalhador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    public List<Telefone> findAll() {
        return telefoneRepository.findAll();
    }

    public Optional<Telefone> findById(Integer id) {
        return telefoneRepository.findById(id);
    }

    public Telefone save(Telefone telefone) {
        return telefoneRepository.save(telefone);
    }

    public void deleteById(Integer id) {
        telefoneRepository.deleteById(id);
    }

    public void updateTelefone(Integer id, String num, Trabalhador idTrabalhador) {
        Optional<Telefone> optionalTelefone = telefoneRepository.findById(id);
        if (optionalTelefone.isPresent()) {
            Telefone telefone = optionalTelefone.get();
            telefone.setNum(num);
            telefone.setIdTrabalhador(idTrabalhador);
            telefoneRepository.save(telefone);
        }
    }
}