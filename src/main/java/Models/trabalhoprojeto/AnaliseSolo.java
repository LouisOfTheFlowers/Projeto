package Models.trabalhoprojeto;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"Analise_solo\"")
public class AnaliseSolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_analise_solo", nullable = false)
    private Integer id;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "resultado_final", length = 150)
    private String resultadoFinal;

    @Column(name = "tipo_analise", length = 150)
    private String tipoAnalise;

    @Column(name = "metodologia", length = 100)
    private String metodologia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_gestor", nullable = false)
    private GestorProducao idGestor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_terreno", nullable = false)
    private Terreno idTerreno;

    @OneToMany(mappedBy = "idAnaliseSolo")
    private Set<AmostraSolo> amostrasSolo = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getResultadoFinal() {
        return resultadoFinal;
    }

    public void setResultadoFinal(String resultadoFinal) {
        this.resultadoFinal = resultadoFinal;
    }

    public String getTipoAnalise() {
        return tipoAnalise;
    }

    public void setTipoAnalise(String tipoAnalise) {
        this.tipoAnalise = tipoAnalise;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }

    public GestorProducao getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(GestorProducao idGestor) {
        this.idGestor = idGestor;
    }

    public Terreno getIdTerreno() { return idTerreno; }

    public void setIdTerreno(Terreno idTerreno) { this.idTerreno = idTerreno; }

    public Set<AmostraSolo> getAmostrasSolo() {
        return amostrasSolo;
    }

    public void setAmostrasSolo(Set<AmostraSolo> amostrasSolo) {
        this.amostrasSolo = amostrasSolo;
    }

}