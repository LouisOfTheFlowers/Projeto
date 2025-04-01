package Services;

import Models.trabalhoprojeto.CronogramaTerreno;
import Models.trabalhoprojeto.CronogramaTerrenoId;
import Repositorios.CronogramaTerrenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CronogramaTerrenoService {

    @Autowired
    private CronogramaTerrenoRepository cronogramaTerrenoRepository;

    public List<CronogramaTerreno> findAll() {
        return cronogramaTerrenoRepository.findAll();
    }

    public Optional<CronogramaTerreno> findById(CronogramaTerrenoId id) {
        return cronogramaTerrenoRepository.findById(id);
    }

    @Transactional
    public CronogramaTerreno save(CronogramaTerreno cronogramaTerreno) {
        return cronogramaTerrenoRepository.save(cronogramaTerreno);
    }

    @Transactional
    public void deleteById(CronogramaTerrenoId id) {
        cronogramaTerrenoRepository.deleteById(id);
    }

    @Transactional
    public void updateCronogramaTerreno(CronogramaTerrenoId id, CronogramaTerreno updatedCronogramaTerreno) {
        Optional<CronogramaTerreno> optionalCronogramaTerreno = cronogramaTerrenoRepository.findById(id);
        if (optionalCronogramaTerreno.isPresent()) {
            CronogramaTerreno cronogramaTerreno = optionalCronogramaTerreno.get();
            cronogramaTerreno.setIdCronograma(updatedCronogramaTerreno.getIdCronograma());
            cronogramaTerreno.setIdTerreno(updatedCronogramaTerreno.getIdTerreno());
            cronogramaTerrenoRepository.save(cronogramaTerreno);
        }
    }
}