package Models.trabalhoprojeto;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "\"Cronograma\"")
public class Cronograma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cronograma", nullable = false)
    private Integer id;

    @Column(name = "dt_inicio_preparo_terreno")
    private LocalDate dtInicioPreparoTerreno;

    @Column(name = "processo_de_preparo", length = 500)
    private String processoDePreparo;

    @Column(name = "processo_de_plantio", length = 500)
    private String processoDePlantio;

    @Column(name = "tipo_horticolas", length = 50)
    private String tipoHorticolas;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_gestor", nullable = false)
    private GestorProducao idGestor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDtInicioPreparoTerreno() {
        return dtInicioPreparoTerreno;
    }

    public void setDtInicioPreparoTerreno(LocalDate dtInicioPreparoTerreno) {
        this.dtInicioPreparoTerreno = dtInicioPreparoTerreno;
    }

    public String getProcessoDePreparo() {
        return processoDePreparo;
    }

    public void setProcessoDePreparo(String processoDePreparo) {
        this.processoDePreparo = processoDePreparo;
    }

    public String getProcessoDePlantio() {
        return processoDePlantio;
    }

    public void setProcessoDePlantio(String processoDePlantio) {
        this.processoDePlantio = processoDePlantio;
    }

    public String getTipoHorticolas() {
        return tipoHorticolas;
    }

    public void setTipoHorticolas(String tipoHorticolas) {
        this.tipoHorticolas = tipoHorticolas;
    }

    public GestorProducao getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(GestorProducao idGestor) {
        this.idGestor = idGestor;
    }

}