package Services;

import Models.trabalhoprojeto.Localidade;
import Repositorios.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class LocalidadeService {

    @Autowired
    private LocalidadeRepository localidadeRepository;

    public List<Localidade> findAll() {
        return localidadeRepository.findAll();
    }

    public Optional<Localidade> findById(String codigoPostal) {
        return localidadeRepository.findById(codigoPostal);
    }

    public Localidade save(Localidade localidade) {
        return localidadeRepository.save(localidade);
    }

    public void deleteById(String codigoPostal) {
        localidadeRepository.deleteById(codigoPostal);
    }

    public void updateLocalidade(String codigoPostal, String localidade, String distrito) {
        Optional<Localidade> optionalLocalidade = localidadeRepository.findById(codigoPostal);
        if (optionalLocalidade.isPresent()) {
            Localidade loc = optionalLocalidade.get();
            loc.setLocalidade(localidade);
            loc.setDistrito(distrito);
            localidadeRepository.save(loc);
        }
    }
}