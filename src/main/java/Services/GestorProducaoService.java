package Services;

import Models.trabalhoprojeto.GestorProducao;
import Repositorios.GestorProducaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class GestorProducaoService {

    @Autowired
    private GestorProducaoRepository gestorProducaoRepository;

    public List<GestorProducao> findAll() {
        return gestorProducaoRepository.findAll();
    }

    public Optional<GestorProducao> findById(Integer id) {
        return gestorProducaoRepository.findById(id);
    }

    public GestorProducao save(GestorProducao gestorProducao) {
        return gestorProducaoRepository.save(gestorProducao);
    }

    public void deleteById(Integer id) {
        gestorProducaoRepository.deleteById(id);
    }

    public void updateGestorProducao(Integer id, String nome, String rua, Integer numeroPorta, String codigoPostal) {
        Optional<GestorProducao> optionalGestorProducao = gestorProducaoRepository.findById(id);
        if (optionalGestorProducao.isPresent()) {
            GestorProducao gestorProducao = optionalGestorProducao.get();
            gestorProducao.setNome(nome);
            gestorProducao.setRua(rua);
            gestorProducao.setNumeroPorta(numeroPorta);
            gestorProducao.setCodigoPostal(codigoPostal);
            gestorProducaoRepository.save(gestorProducao);
        }
    }
}
