package Models.trabalhoprojeto;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Proposta_Plantio\"")
public class PropostaPlantio {
    @Id
    @Column(name = "id_proposta", nullable = false)
    private Integer id;

    @Column(name = "horticolas", length = 150)
    private String horticolas;

    @Column(name = "altura_do_ano", length = 150)
    private String alturaDoAno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_agricultor", nullable = false)
    private Agricultor idAgricultor;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_gestor", nullable = false)
    private GestorProducao idGestor;

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

    public Agricultor getIdAgricultor() {
        return idAgricultor;
    }

    public void setIdAgricultor(Agricultor idAgricultor) {
        this.idAgricultor = idAgricultor;
    }

    public GestorProducao getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(GestorProducao idGestor) {
        this.idGestor = idGestor;
    }

}