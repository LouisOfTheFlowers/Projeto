package Services;

import Models.trabalhoprojeto.AmostraSolo;
import Models.trabalhoprojeto.AnaliseSolo;
import Models.trabalhoprojeto.Terreno;
import Repositorios.AmostraSoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class AmostraSoloService {

    @Autowired
    private AmostraSoloRepository amostraSoloRepository;

    public List<AmostraSolo> findAll() {
        return amostraSoloRepository.findAll();
    }

    public Optional<AmostraSolo> findById(Integer id) {
        return amostraSoloRepository.findById(id);
    }

    public AmostraSolo save(AmostraSolo amostraSolo) {
        return amostraSoloRepository.save(amostraSolo);
    }

    public void deleteById(Integer id) {
        amostraSoloRepository.deleteById(id);
    }

    public void updateAmostraSolo(Integer id, String tipoAmostra, AnaliseSolo idAnaliseSolo, Terreno idTerreno) {
        Optional<AmostraSolo> optionalAmostraSolo = amostraSoloRepository.findById(id);
        if (optionalAmostraSolo.isPresent()) {
            AmostraSolo amostraSolo = optionalAmostraSolo.get();
            amostraSolo.setTipoAmostra(tipoAmostra);
            amostraSolo.setIdAnaliseSolo(idAnaliseSolo);
            amostraSolo.setIdTerreno(idTerreno);
            amostraSoloRepository.save(amostraSolo);
        }
    }
}