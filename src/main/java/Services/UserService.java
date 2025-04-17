package Services;

import Models.trabalhoprojeto.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean registarAgricultor(User user, Trabalhador trabalhador, Agricultor agricultor) {
        try {
            em.persist(trabalhador);
            agricultor.setIdTrabalhador(trabalhador);
            agricultor.setCodigoPostal(trabalhador.getCodigoPostal().getCodigoPostal());
            em.persist(agricultor);

            user.setTrabalhador(trabalhador);
            em.persist(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean registarGestor(User user, Trabalhador trabalhador, GestorProducao gestor) {
        try {
            em.persist(trabalhador);
            gestor.setIdTrabalhador(trabalhador);
            gestor.setCodigoPostal(trabalhador.getCodigoPostal().getCodigoPostal());
            em.persist(gestor);

            user.setTrabalhador(trabalhador);
            em.persist(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean registarAnalista(User user, Trabalhador trabalhador, AnalistaDado analista) {
        try {
            em.persist(trabalhador);
            analista.setIdTrabalhador(trabalhador);
            analista.setCodigoPostal(trabalhador.getCodigoPostal().getCodigoPostal());
            em.persist(analista);

            user.setTrabalhador(trabalhador);
            em.persist(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
