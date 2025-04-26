package Models.trabalhoprojeto;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"Gestor_Producao\"")
public class GestorProducao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // OU AUTO, dependendo do banco
    @Column(name = "id_gestor")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_trabalhador", nullable = false)
    private Trabalhador idTrabalhador;

    @Column(name = "nome", length = 150)
    private String nome;

    @Column(name = "rua", length = 150)
    private String rua;

    @Column(name = "numero_porta")
    private Integer numeroPorta;

    @Column(name = "\"codigo-postal\"", nullable = false, length = 20)
    private String codigoPostal;

    @OneToMany(mappedBy = "idGestor")
    private Set<AnaliseSolo> analisesSolo = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idGestor")
    private Set<Cronograma> cronogramas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idGestor")
    private Set<PropostaPlantio> propostasPlantio = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Trabalhador getIdTrabalhador() {
        return idTrabalhador;
    }

    public void setIdTrabalhador(Trabalhador idTrabalhador) {
        this.idTrabalhador = idTrabalhador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumeroPorta() {
        return numeroPorta;
    }

    public void setNumeroPorta(Integer numeroPorta) {
        this.numeroPorta = numeroPorta;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Set<AnaliseSolo> getAnalisesSolo() {
        return analisesSolo;
    }

    public void setAnalisesSolo(Set<AnaliseSolo> analisesSolo) {
        this.analisesSolo = analisesSolo;
    }

    public Set<Cronograma> getCronogramas() {
        return cronogramas;
    }

    public void setCronogramas(Set<Cronograma> cronogramas) {
        this.cronogramas = cronogramas;
    }

    public Set<PropostaPlantio> getPropostasPlantio() {
        return propostasPlantio;
    }

    public void setPropostasPlantio(Set<PropostaPlantio> propostasPlantio) {
        this.propostasPlantio = propostasPlantio;
    }

    @Override
    public String toString() {
        return nome;
    }

}