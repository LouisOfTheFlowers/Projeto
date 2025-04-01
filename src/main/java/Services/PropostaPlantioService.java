package Services;

import Models.trabalhoprojeto.Agricultor;
import Models.trabalhoprojeto.GestorProducao;
import Models.trabalhoprojeto.PropostaPlantio;
import Repositorios.PropostaPlantioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class PropostaPlantioService {

    @Autowired
    private PropostaPlantioRepository propostaPlantioRepository;

    public List<PropostaPlantio> findAll() {
        return propostaPlantioRepository.findAll();
    }

    public Optional<PropostaPlantio> findById(Integer id) {
        return propostaPlantioRepository.findById(id);
    }

    public PropostaPlantio save(PropostaPlantio propostaPlantio) {
        return propostaPlantioRepository.save(propostaPlantio);
    }

    public void deleteById(Integer id) {
        propostaPlantioRepository.deleteById(id);
    }

    public void updatePropostaPlantio(Integer id, String horticolas, String alturaDoAno, Agricultor idAgricultor, GestorProducao idGestor) {
        Optional<PropostaPlantio> optionalPropostaPlantio = propostaPlantioRepository.findById(id);
        if (optionalPropostaPlantio.isPresent()) {
            PropostaPlantio propostaPlantio = optionalPropostaPlantio.get();
            propostaPlantio.setHorticolas(horticolas);
            propostaPlantio.setAlturaDoAno(alturaDoAno);
            propostaPlantio.setIdAgricultor(idAgricultor);
            propostaPlantio.setIdGestor(idGestor);
            propostaPlantioRepository.save(propostaPlantio);
        }
    }
}