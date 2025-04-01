package Repositorios;

import Models.trabalhoprojeto.CronogramaTerreno;
import Models.trabalhoprojeto.CronogramaTerrenoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CronogramaTerrenoRepository extends JpaRepository<CronogramaTerreno, CronogramaTerrenoId> {
}