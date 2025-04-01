package Repositorios;

import Models.trabalhoprojeto.AnalistaDado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalistaDadoRepository extends JpaRepository<AnalistaDado, Integer> {
}