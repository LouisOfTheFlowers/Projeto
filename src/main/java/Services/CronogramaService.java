package Services;

import Repositorios.CronogramaRepository;
import Models.trabalhoprojeto.Cronograma;
import Models.trabalhoprojeto.GestorProducao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class CronogramaService {

    @Autowired
    private CronogramaRepository cronogramaRepository;

    public List<Cronograma> findAll() {
        return cronogramaRepository.findAll();
    }

    public Optional<Cronograma> findById(Integer id) {
        return cronogramaRepository.findById(id);
    }

    public Cronograma save(Cronograma cronograma) {
        return cronogramaRepository.save(cronograma);
    }

    public void deleteById(Integer id) {
        cronogramaRepository.deleteById(id);
    }

    public void updateCronograma(Integer id, LocalDate dtInicioPreparoTerreno, String processoDePreparo, String processoDePlantio, String tipoHorticolas, GestorProducao idGestor) {
        Optional<Cronograma> optionalCronograma = cronogramaRepository.findById(id);
        if (optionalCronograma.isPresent()) {
            Cronograma cronograma = optionalCronograma.get();
            cronograma.setDtInicioPreparoTerreno(dtInicioPreparoTerreno);
            cronograma.setProcessoDePreparo(processoDePreparo);
            cronograma.setProcessoDePlantio(processoDePlantio);
            cronograma.setTipoHorticolas(tipoHorticolas);
            cronograma.setIdGestor(idGestor);
            cronogramaRepository.save(cronograma);
        }
    }
}