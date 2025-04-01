package Models.trabalhoprojeto;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Email\"")
public class Email {
    @Id
    @Column(name = "id_email", nullable = false)
    private Integer id;

    @Column(name = "\"endereço\"", length = 50)
    private String endereço;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_trabalhador", nullable = false)
    private Trabalhador idTrabalhador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public Trabalhador getIdTrabalhador() {
        return idTrabalhador;
    }

    public void setIdTrabalhador(Trabalhador idTrabalhador) {
        this.idTrabalhador = idTrabalhador;
    }

}