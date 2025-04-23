package Models.trabalhoprojeto;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Telefone\"")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telefone", nullable = false)
    private Integer id;

    @Column(name = "num", length = 15)
    private String num;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_trabalhador", nullable = false)
    private Trabalhador idTrabalhador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Trabalhador getIdTrabalhador() {
        return idTrabalhador;
    }

    public void setIdTrabalhador(Trabalhador idTrabalhador) {
        this.idTrabalhador = idTrabalhador;
    }

}