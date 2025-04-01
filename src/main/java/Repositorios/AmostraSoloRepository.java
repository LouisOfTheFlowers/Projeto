package Repositorios;

import Models.trabalhoprojeto.AmostraSolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmostraSoloRepository extends JpaRepository<AmostraSolo, Integer> {
}