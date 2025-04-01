package Services;

import Repositorios.AnalistaDadoRepository;
import Models.trabalhoprojeto.AnalistaDado;
import Models.trabalhoprojeto.Trabalhador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class AnalistaDadoService {

    @Autowired
    private AnalistaDadoRepository analistaDadoRepository;

    public List<AnalistaDado> findAll() {
        return analistaDadoRepository.findAll();
    }

    public Optional<AnalistaDado> findById(Integer id) {
        return analistaDadoRepository.findById(id);
    }

    public AnalistaDado save(AnalistaDado analistaDado) {
        return analistaDadoRepository.save(analistaDado);
    }

    public void deleteById(Integer id) {
        analistaDadoRepository.deleteById(id);
    }

    public void updateAnalistaDado(Integer id, String nome, String rua, Integer numeroPorta, String codigoPostal, Trabalhador idTrabalhador) {
        Optional<AnalistaDado> optionalAnalistaDado = analistaDadoRepository.findById(id);
        if (optionalAnalistaDado.isPresent()) {
            AnalistaDado analistaDado = optionalAnalistaDado.get();
            analistaDado.setNome(nome);
            analistaDado.setRua(rua);
            analistaDado.setNumeroPorta(numeroPorta);
            analistaDado.setCodigoPostal(codigoPostal);
            analistaDado.setIdTrabalhador(idTrabalhador);
            analistaDadoRepository.save(analistaDado);
        }
    }
}