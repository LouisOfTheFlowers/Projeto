package Services;

import Models.trabalhoprojeto.Terreno;
import Repositorios.AnaliseSoloRepository;
import Models.trabalhoprojeto.AnaliseSolo;
import Models.trabalhoprojeto.GestorProducao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class AnaliseSoloService {

    @Autowired
    private AnaliseSoloRepository analiseSoloRepository;

    @Transactional
    public List<AnaliseSolo> findAllEager() {
        List<AnaliseSolo> analises = analiseSoloRepository.findAll();

        for (AnaliseSolo analise : analises) {
            Hibernate.initialize(analise.getIdGestor());
            Hibernate.initialize(analise.getIdTerreno());
        }

        return analises;
    }


    public Optional<AnaliseSolo> findById(Integer id) {
        return analiseSoloRepository.findById(id);
    }

    public AnaliseSolo save(AnaliseSolo analiseSolo) {
        return analiseSoloRepository.save(analiseSolo);
    }

    public void deleteById(Integer id) {
        analiseSoloRepository.deleteById(id);
    }

    public void updateAnaliseSolo(Integer id, LocalDate data, String resultadoFinal, String tipoAnalise,
                                  String metodologia, GestorProducao idGestor, Terreno idTerreno) {
        Optional<AnaliseSolo> optionalAnaliseSolo = analiseSoloRepository.findById(id);
        if (optionalAnaliseSolo.isPresent()) {
            AnaliseSolo analiseSolo = optionalAnaliseSolo.get();
            analiseSolo.setData(data);
            analiseSolo.setResultadoFinal(resultadoFinal);
            analiseSolo.setTipoAnalise(tipoAnalise);
            analiseSolo.setMetodologia(metodologia);
            analiseSolo.setIdGestor(idGestor);
            analiseSolo.setIdTerreno(idTerreno);
            analiseSoloRepository.save(analiseSolo);
        }
    }

}
