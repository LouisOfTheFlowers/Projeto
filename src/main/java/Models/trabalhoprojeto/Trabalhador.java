package Models.trabalhoprojeto;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"Trabalhador\"")
public class Trabalhador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trabalhador", nullable = false)
    private Integer id;

    @Column(name = "nome", length = 150)
    private String nome;

    @Column(name = "rua", length = 150)
    private String rua;

    @Column(name = "numero_porta")
    private Integer numeroPorta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "\"codigo-postal\"", nullable = false)
    private Localidade codigoPostal;

    @OneToMany(mappedBy = "idTrabalhador")
    private Set<Agricultor> agricultores = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idTrabalhador")
    private Set<AnalistaDado> analistaDados = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idTrabalhador")
    private Set<Email> emails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idTrabalhador")
    private Set<GestorProducao> gestoresProducao = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idTrabalhador")
    private Set<Telefone> telefones = new LinkedHashSet<>();

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Localidade getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Localidade codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Set<Agricultor> getAgricultores() {
        return agricultores;
    }

    public void setAgricultores(Set<Agricultor> agricultores) {
        this.agricultores = agricultores;
    }

    public Set<AnalistaDado> getAnalistaDados() {
        return analistaDados;
    }

    public void setAnalistaDados(Set<AnalistaDado> analistaDados) {
        this.analistaDados = analistaDados;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    public Set<GestorProducao> getGestoresProducao() {
        return gestoresProducao;
    }

    public void setGestoresProducao(Set<GestorProducao> gestoresProducao) {
        this.gestoresProducao = gestoresProducao;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }
}
