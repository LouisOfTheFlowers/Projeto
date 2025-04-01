package Models.trabalhoprojeto;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Produto/Terreno\"")
public class ProdutoTerreno {
    @EmbeddedId
    private ProdutoTerrenoId id;

    @MapsId("idTerreno")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_terreno", nullable = false)
    private Terreno idTerreno;

    @MapsId("idProduto")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto idProduto;

    public ProdutoTerrenoId getId() {
        return id;
    }

    public void setId(ProdutoTerrenoId id) {
        this.id = id;
    }

    public Terreno getIdTerreno() {
        return idTerreno;
    }

    public void setIdTerreno(Terreno idTerreno) {
        this.idTerreno = idTerreno;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

}