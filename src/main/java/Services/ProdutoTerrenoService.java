package Services;

import Models.trabalhoprojeto.ProdutoTerreno;
import Models.trabalhoprojeto.ProdutoTerrenoId;
import Repositorios.ProdutoTerrenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class ProdutoTerrenoService {

    @Autowired
    private ProdutoTerrenoRepository produtoTerrenoRepository;

    public List<ProdutoTerreno> findAll() {
        return produtoTerrenoRepository.findAll();
    }

    public Optional<ProdutoTerreno> findById(ProdutoTerrenoId id) {
        return produtoTerrenoRepository.findById(id);
    }

    public ProdutoTerreno save(ProdutoTerreno produtoTerreno) {
        return produtoTerrenoRepository.save(produtoTerreno);
    }

    public void deleteById(ProdutoTerrenoId id) {
        produtoTerrenoRepository.deleteById(id);
    }
}