package Models.trabalhoprojeto;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "\"Localidade\"")
public class Localidade {

    @Id
    @Column(name = "\"codigo-postal\"", length = 20, nullable = false)
    private String codigoPostal;

    @Column(name = "localidade", length = 100)
    private String localidade;

    @Column(name = "distrito", length = 100)
    private String distrito;

    @OneToMany(mappedBy = "codigoPostal")
    private Set<Trabalhador> trabalhadores = new LinkedHashSet<>();

    // Getters e Setters
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public Set<Trabalhador> getTrabalhadores() {
        return trabalhadores;
    }

    public void setTrabalhadores(Set<Trabalhador> trabalhadores) {
        this.trabalhadores = trabalhadores;
    }
}
