package Services;

import Models.trabalhoprojeto.Trabalhador;
import Repositorios.TrabalhadorRepository;
import Models.trabalhoprojeto.Localidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class TrabalhadorService {

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    public List<Trabalhador> findAll() {
        return trabalhadorRepository.findAll();
    }

    public Optional<Trabalhador> findById(Integer id) {
        return trabalhadorRepository.findById(id);
    }

    public Trabalhador save(Trabalhador trabalhador) {
        return trabalhadorRepository.save(trabalhador);
    }

    public void deleteById(Integer id) {
        trabalhadorRepository.deleteById(id);
    }

    public void updateTrabalhador(Integer id, String nome, String rua, Integer numeroPorta, Localidade codigoPostal) {
        Optional<Trabalhador> optionalTrabalhador = trabalhadorRepository.findById(id);
        if (optionalTrabalhador.isPresent()) {
            Trabalhador trabalhador = optionalTrabalhador.get();
            trabalhador.setNome(nome);
            trabalhador.setRua(rua);
            trabalhador.setNumeroPorta(numeroPorta);
            trabalhador.setCodigoPostal(codigoPostal);
            trabalhadorRepository.save(trabalhador);
        }
    }
}