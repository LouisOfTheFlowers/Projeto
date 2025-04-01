package Models.trabalhoprojeto;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Amostra_Solo\"")
public class AmostraSolo {
    @Id
    @Column(name = "id_amostra", nullable = false)
    private Integer id;

    @Column(name = "tipo_amostra", length = 100)
    private String tipoAmostra;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_analise_solo", nullable = false)
    private AnaliseSolo idAnaliseSolo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_terreno", nullable = false)
    private Terreno idTerreno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoAmostra() {
        return tipoAmostra;
    }

    public void setTipoAmostra(String tipoAmostra) {
        this.tipoAmostra = tipoAmostra;
    }

    public AnaliseSolo getIdAnaliseSolo() {
        return idAnaliseSolo;
    }

    public void setIdAnaliseSolo(AnaliseSolo idAnaliseSolo) {
        this.idAnaliseSolo = idAnaliseSolo;
    }

    public Terreno getIdTerreno() {
        return idTerreno;
    }

    public void setIdTerreno(Terreno idTerreno) {
        this.idTerreno = idTerreno;
    }

}