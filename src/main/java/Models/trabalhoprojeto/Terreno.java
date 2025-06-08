package Models.trabalhoprojeto;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"terreno\"")
public class Terreno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_terreno", nullable = false)
    private Integer id;

    @Column(name = "area", length = 15)
    private String area;

    @Column(name = "coordenadas", length = 20)
    private String coordenadas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_agricultor", nullable = false)
    private Agricultor idAgricultor;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Agricultor getIdAgricultor() {
        return idAgricultor;
    }

    public void setIdAgricultor(Agricultor idAgricultor) {
        this.idAgricultor = idAgricultor;
    }

}