package Services;

import Models.trabalhoprojeto.Relatorio;
import Repositorios.RelatorioRepository;
import Models.trabalhoprojeto.AnalistaDado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Repositorios"})
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;

    public List<Relatorio> findAll() {
        return relatorioRepository.findAll();
    }

    public Optional<Relatorio> findById(Integer id) {
        return relatorioRepository.findById(id);
    }

    public Relatorio save(Relatorio relatorio) {
        return relatorioRepository.save(relatorio);
    }

    public void deleteById(Integer id) {
        relatorioRepository.deleteById(id);
    }

    public void updateRelatorio(Integer id, LocalDate data, String tipoRelatorio, String descricao, String tema, String titulo, AnalistaDado idAnalista) {
        Optional<Relatorio> optionalRelatorio = relatorioRepository.findById(id);
        if (optionalRelatorio.isPresent()) {
            Relatorio relatorio = optionalRelatorio.get();
            relatorio.setData(data);
            relatorio.setTipoRelatorio(tipoRelatorio);
            relatorio.setDescricao(descricao);
            relatorio.setTema(tema);
            relatorio.setTitulo(titulo);
            relatorio.setIdAnalista(idAnalista);
            relatorioRepository.save(relatorio);
        }
    }
}