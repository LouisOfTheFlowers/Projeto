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
@ComponentScan(basePackages = {"Models.trabalhoprojeto", "Services"})
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

		Localidade localidade = new Localidade();
		localidade.setTrabalhadores(null);
		localidade.setCodigoPostal("1234-567");
		localidade.setLocalidade("Viana");
		localidade.setDistrito("Castelo");
		localidadeService.save(localidade);

		Trabalhador trabalhador1 = new Trabalhador();
		trabalhador1.setId(1);
		trabalhador1.setCodigoPostal(localidade);
		trabalhador1.setNome("João");
		trabalhador1.setRua("Rua da Paz");
		trabalhador1.setNumeroPorta(12);
		trabalhadorService.save(trabalhador1);

		Trabalhador trabalhador2 = new Trabalhador();
		trabalhador2.setId(2);
		trabalhador2.setCodigoPostal(localidade);
		trabalhador2.setNome("Jorge");
		trabalhador2.setRua("Rua da Luz");
		trabalhador2.setNumeroPorta(13);
		trabalhadorService.save(trabalhador2);

		Trabalhador trabalhador3 = new Trabalhador();
		trabalhador3.setId(3);
		trabalhador3.setCodigoPostal(localidade);
		trabalhador3.setNome("Jonas");
		trabalhador3.setRua("Rua da Alegria");
		trabalhador3.setNumeroPorta(14);
		trabalhadorService.save(trabalhador3);

		Email email = new Email();
		email.setId(1);
		email.setEndereço("email@gmail.com");
		email.setIdTrabalhador(trabalhador1);
		emailService.save(email);

		Telefone telefone = new Telefone();
		telefone.setId(1);
		telefone.setNum("123456789");
		telefone.setIdTrabalhador(trabalhador1);
		telefoneService.save(telefone);

		Agricultor agricultor = new Agricultor();
		agricultor.setId(1);
		agricultor.setNome("Joao");
		agricultor.setRua("Rua da Paz");
		agricultor.setNumeroPorta(12);
		agricultor.setCodigoPostal(localidade.getCodigoPostal());
		agricultor.setIdTrabalhador(trabalhador1);
		agricultorService.save(agricultor);

		AnalistaDado analista = new AnalistaDado();
		analista.setId(1);
		analista.setNome("Jorge");
		analista.setRua("Rua da Luz");
		analista.setNumeroPorta(13);
		analista.setCodigoPostal(localidade.getCodigoPostal());
		analista.setIdTrabalhador(trabalhador2);
		analistaDadoService.save(analista);

		GestorProducao gestorProducao = new GestorProducao();
		gestorProducao.setId(1);
		gestorProducao.setNome("Jonas");
		gestorProducao.setRua("Rua da Alegria");
		gestorProducao.setNumeroPorta(14);
		gestorProducao.setCodigoPostal(localidade.getCodigoPostal());
		gestorProducao.setIdTrabalhador(trabalhador3);
		gestorProducaoService.save(gestorProducao);

		Terreno terreno = new Terreno();
		terreno.setId(1);
		terreno.setArea("1000");
		terreno.setCoordenadas("5° 46' 48 S");
		terreno.setIdAgricultor(agricultor);
		terrenoService.save(terreno);

		Produto produto = new Produto();
		produto.setId(1);
		produto.setTipoProduto("Milho");
		produtoService.save(produto);

		ProdutoTerrenoId produtoTerrenoId = new ProdutoTerrenoId();
		produtoTerrenoId.setIdProduto(produto.getId());
		produtoTerrenoId.setIdTerreno(terreno.getId());

		ProdutoTerreno produtoTerreno = new ProdutoTerreno();
		produtoTerreno.setId(produtoTerrenoId);
		produtoTerreno.setIdProduto(produto);
		produtoTerreno.setIdTerreno(terreno);
		produtoTerrenoService.save(produtoTerreno);

		Relatorio relatorio = new Relatorio();
		relatorio.setId(1);
		relatorio.setDescricao("Relatorio de produção de milho na região de Viana");
		relatorio.setTipoRelatorio("Produção");
		relatorio.setTema("Produção de milho");
		relatorio.setTitulo("Relatorio de produção de milho");
		relatorio.setData(LocalDate.now());
		relatorio.setIdAnalista(analista);
		relatorioService.save(relatorio);

		Cronograma cronograma = new Cronograma();
		cronograma.setId(1);
		cronograma.setDtInicioPreparoTerreno(LocalDate.now());
		cronograma.setProcessoDePreparo("Lavrar o terreno");
		cronograma.setProcessoDePlantio("Plantar milho");
		cronograma.setTipoHorticolas("Milho");
		cronograma.setIdGestor(gestorProducao);
		cronogramaService.save(cronograma);

		AnaliseSolo analiseSolo = new AnaliseSolo();
		analiseSolo.setId(1);
		analiseSolo.setData(LocalDate.now());
		analiseSolo.setResultadoFinal("Solo pronto para plantio");
		analiseSolo.setTipoAnalise("Analise quimica");
		analiseSolo.setIdGestor(gestorProducao);
		analiseSolo.setMetodologia("Testes de PH");
		analiseSoloService.save(analiseSolo);

		AmostraSolo amostraSolo = new AmostraSolo();
		amostraSolo.setId(1);
		amostraSolo.setTipoAmostra("Amostra de solo");
		amostraSolo.setIdAnaliseSolo(analiseSolo);
		amostraSolo.setIdTerreno(terreno);
		amostraSoloService.save(amostraSolo);

		PropostaPlantio propostaPlantio = new PropostaPlantio();
		propostaPlantio.setId(1);
		propostaPlantio.setHorticolas("Milho");
		propostaPlantio.setAlturaDoAno("Maio");
		propostaPlantio.setIdAgricultor(agricultor);
		propostaPlantio.setIdGestor(gestorProducao);
		propostaPlantioService.save(propostaPlantio);

		CronogramaTerrenoId cronogramaTerrenoId = new CronogramaTerrenoId();
		cronogramaTerrenoId.setIdCronograma(cronograma.getId());
		cronogramaTerrenoId.setIdTerreno(terreno.getId());

		CronogramaTerreno cronogramaTerreno = new CronogramaTerreno();
		cronogramaTerreno.setId(cronogramaTerrenoId);
		cronogramaTerreno.setIdCronograma(cronograma);
		cronogramaTerreno.setIdTerreno(terreno);
		cronogramaTerrenoService.save(cronogramaTerreno);


	}
}