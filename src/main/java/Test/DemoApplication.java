package Test;

import Models.trabalhoprojeto.*;
import Services.*;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.time.LocalDate;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "Repositorios")
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Services", "Test"})

@EntityScan(basePackages = "Models.trabalhoprojeto")
public class DemoApplication  implements CommandLineRunner  {



	@Autowired
	private LocalidadeService localidadeService;
	@Autowired
	private AgricultorService agricultorService;
	@Autowired
	private TrabalhadorService trabalhadorService;
	@Autowired
	private AnalistaDadoService analistaDadoService;
	@Autowired
	private GestorProducaoService gestorProducaoService;
	@Autowired
	private TerrenoService terrenoService;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ProdutoTerrenoService produtoTerrenoService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private TelefoneService telefoneService;
	@Autowired
	private RelatorioService relatorioService;
	@Autowired
	private CronogramaService cronogramaService;
	@Autowired
	private AnaliseSoloService analiseSoloService;
	@Autowired
	private AmostraSoloService amostraSoloService;
	@Autowired
	private PropostaPlantioService propostaPlantioService;
	@Autowired
	private CronogramaTerrenoService cronogramaTerrenoService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		JavaFXApp.launch(JavaFXApp.class, args);

	}

	@Override
	public void run(String... args) throws Exception {




	}
}