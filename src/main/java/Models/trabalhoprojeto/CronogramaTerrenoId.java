package Models.trabalhoprojeto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CronogramaTerrenoId implements Serializable {
    private static final long serialVersionUID = 3331143621230744808L;
    @Column(name = "id_cronograma", nullable = false)
    private Integer idCronograma;

    @Column(name = "id_terreno", nullable = false)
    private Integer idTerreno;

    public Integer getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(Integer idCronograma) {
        this.idCronograma = idCronograma;
    }

    public Integer getIdTerreno() {
        return idTerreno;
    }

    public void setIdTerreno(Integer idTerreno) {
        this.idTerreno = idTerreno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CronogramaTerrenoId entity = (CronogramaTerrenoId) o;
        return Objects.equals(this.idCronograma, entity.idCronograma) &&
                Objects.equals(this.idTerreno, entity.idTerreno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCronograma, idTerreno);
    }

}