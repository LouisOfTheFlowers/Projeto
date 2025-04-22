package Repositorios;

import Models.trabalhoprojeto.PropostaPlantio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaPlantioRepository extends JpaRepository<PropostaPlantio, Integer> {

}
