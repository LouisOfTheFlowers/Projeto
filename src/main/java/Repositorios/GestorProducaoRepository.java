package Repositorios;

import Models.trabalhoprojeto.GestorProducao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestorProducaoRepository extends JpaRepository<GestorProducao, Integer> {
}