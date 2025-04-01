package Repositorios;

import Models.trabalhoprojeto.AnaliseSolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseSoloRepository extends JpaRepository<AnaliseSolo, Integer> {
}