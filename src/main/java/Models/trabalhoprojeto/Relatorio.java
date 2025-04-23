package Models.trabalhoprojeto;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "\"Relatorio\"")
public class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <--- ESTA LINHA!
    @Column(name = "id_relatorio", nullable = false)
    private Integer id;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "tipo_relatorio", length = 100)
    private String tipoRelatorio;

    @Column(name = "descricao", length = 250)
    private String descricao;

    @Column(name = "tema", length = 100)
    private String tema;

    @Column(name = "titulo", length = 100)
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_analista", nullable = false)
    private AnalistaDado idAnalista;
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

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public AnalistaDado getIdAnalista() {
        return idAnalista;
    }

    public void setIdAnalista(AnalistaDado idAnalista) {
        this.idAnalista = idAnalista;
    }

}