package Repositorios;

import Models.trabalhoprojeto.ProdutoTerreno;
import Models.trabalhoprojeto.ProdutoTerrenoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoTerrenoRepository extends JpaRepository<ProdutoTerreno, ProdutoTerrenoId> {
}