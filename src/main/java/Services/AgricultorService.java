package Services;

import Models.trabalhoprojeto.Agricultor;
import Models.trabalhoprojeto.Trabalhador;
import Repositorios.AgricultorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class AgricultorService {

    @Autowired
    private AgricultorRepository agricultorRepository;

    public List<Agricultor> findAll() {
        return agricultorRepository.findAll();
    }

    public Optional<Agricultor> findById(Integer id) {
        return agricultorRepository.findById(id);
    }

    public Agricultor save(Agricultor agricultor) {
        return agricultorRepository.save(agricultor);
    }

    public void deleteById(Integer id) {
        agricultorRepository.deleteById(id);
    }

    public void updateAgricultor(Integer id, String nome, String rua, Integer numeroPorta, String codigoPostal, Trabalhador idTrabalhador) {
        Optional<Agricultor> optionalAgricultor = agricultorRepository.findById(id);
        if (optionalAgricultor.isPresent()) {
            Agricultor agricultor = optionalAgricultor.get();
            agricultor.setNome(nome);
            agricultor.setRua(rua);
            agricultor.setNumeroPorta(numeroPorta);
            agricultor.setCodigoPostal(codigoPostal);
            agricultor.setIdTrabalhador(idTrabalhador);
            agricultorRepository.save(agricultor);
        }
    }
}