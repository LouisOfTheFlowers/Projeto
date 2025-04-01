package Services;

import Models.trabalhoprojeto.Produto;
import Models.trabalhoprojeto.Terreno;
import Repositorios.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Integer id) {
        return produtoRepository.findById(id);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteById(Integer id) {
        produtoRepository.deleteById(id);
    }

    public void updateProduto(Integer id, String tipoProduto, Set<Terreno> terrenos) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            produto.setTipoProduto(tipoProduto);
            produto.setTerrenos(terrenos);
            produtoRepository.save(produto);
        }
    }
}