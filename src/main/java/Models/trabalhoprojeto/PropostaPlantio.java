package Models.trabalhoprojeto;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Proposta_Plantio\"")
public class PropostaPlantio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proposta_plantio_gen")
    @SequenceGenerator(name = "proposta_plantio_gen", sequenceName = "proposta_plantio_seq", allocationSize = 1)
    @Column(name = "id_proposta", nullable = false)
    private Integer id;

    @Column(name = "horticolas", length = 150)
    private String horticolas;

    @Column(name = "altura_do_ano", length = 150)
    private String alturaDoAno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_gestor", nullable = false)
    private GestorProducao idGestor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_agricultor", nullable = false)
    private Agricultor idAgricultor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_terreno", nullable = false)
    private Terreno idTerreno;

    @Column(name = "estado")
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHorticolas() {
        return horticolas;
    }

    public void setHorticolas(String horticolas) {
        this.horticolas = horticolas;
    }

    public String getAlturaDoAno() {
        return alturaDoAno;
    }

    public void setAlturaDoAno(String alturaDoAno) {
        this.alturaDoAno = alturaDoAno;
    }

    public GestorProducao getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(GestorProducao idGestor) {
        this.idGestor = idGestor;
    }

    public Agricultor getIdAgricultor() {
        return idAgricultor;
    }

    public void setIdAgricultor(Agricultor idAgricultor) {
        this.idAgricultor = idAgricultor;
    }

    public Terreno getIdTerreno() {
        return idTerreno;
    }

    public void setIdTerreno(Terreno idTerreno) {
        this.idTerreno = idTerreno;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}