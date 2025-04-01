package Repositorios;

import Models.trabalhoprojeto.Terreno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerrenoRepository extends JpaRepository<Terreno, Integer> {
}