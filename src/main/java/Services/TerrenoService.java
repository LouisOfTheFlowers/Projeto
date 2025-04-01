package Services;

import Models.trabalhoprojeto.Terreno;
import Models.trabalhoprojeto.Agricultor;
import Repositorios.TerrenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class TerrenoService {

    @Autowired
    private TerrenoRepository terrenoRepository;

    public List<Terreno> findAll() {
        return terrenoRepository.findAll();
    }

    public Optional<Terreno> findById(Integer id) {
        return terrenoRepository.findById(id);
    }

    public Terreno save(Terreno terreno) {
        return terrenoRepository.save(terreno);
    }

    public void deleteById(Integer id) {
        terrenoRepository.deleteById(id);
    }

    public void updateTerreno(Integer id, String area, String coordenadas, Agricultor idAgricultor) {
        Optional<Terreno> optionalTerreno = terrenoRepository.findById(id);
        if (optionalTerreno.isPresent()) {
            Terreno terreno = optionalTerreno.get();
            terreno.setArea(area);
            terreno.setCoordenadas(coordenadas);
            terreno.setIdAgricultor(idAgricultor);
            terrenoRepository.save(terreno);
        }
    }
}