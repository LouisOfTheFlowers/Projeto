package Services;

import Models.trabalhoprojeto.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean registarAgricultor(User user, Trabalhador trabalhador, Agricultor agricultor, Email email, Telefone telefone) {
        try {
            em.persist(trabalhador);
            agricultor.setIdTrabalhador(trabalhador);
            agricultor.setCodigoPostal(trabalhador.getCodigoPostal().getCodigoPostal());
            em.persist(agricultor);

            user.setTrabalhador(trabalhador);
            em.persist(user);
            email.setIdTrabalhador(trabalhador);
            email.setEndereço(user.getEmail());
            em.persist(email);


            telefone.setIdTrabalhador(trabalhador);
            em.persist(telefone);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean registarGestor(User user, Trabalhador trabalhador, GestorProducao gestor, Email email, Telefone telefone) {
        try {
            em.persist(trabalhador);
            gestor.setIdTrabalhador(trabalhador);
            gestor.setCodigoPostal(trabalhador.getCodigoPostal().getCodigoPostal());
            em.persist(gestor);

            user.setTrabalhador(trabalhador);
            em.persist(user);
            email.setIdTrabalhador(trabalhador);
            email.setEndereço(user.getEmail());
            em.persist(email);

            telefone.setIdTrabalhador(trabalhador);
            em.persist(telefone);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean registarAnalista(User user, Trabalhador trabalhador, AnalistaDado analista, Email email, Telefone telefone) {
        try {
            em.persist(trabalhador);
            analista.setIdTrabalhador(trabalhador);
            analista.setCodigoPostal(trabalhador.getCodigoPostal().getCodigoPostal());
            em.persist(analista);

            user.setTrabalhador(trabalhador);
            em.persist(user);
            email.setIdTrabalhador(trabalhador);
            email.setEndereço(user.getEmail());
            em.persist(email);

            telefone.setIdTrabalhador(trabalhador);
            em.persist(telefone);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    @Transactional
    public String autenticarComRole(String username, String password) {
        User user = findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            Trabalhador trabalhador = user.getTrabalhador();
            if (trabalhador != null) {
                if (trabalhador.getAgricultores() != null && !trabalhador.getAgricultores().isEmpty()) return "agricultor";
                if (trabalhador.getGestoresProducao() != null && !trabalhador.getGestoresProducao().isEmpty()) return "gestor";
                if (trabalhador.getAnalistaDados() != null && !trabalhador.getAnalistaDados().isEmpty()) return "analista";
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Agricultor> findAllAgricultores() {
        return em.createQuery("SELECT a FROM Agricultor a").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<GestorProducao> findAllGestores() {
        return em.createQuery("SELECT g FROM GestorProducao g").getResultList();
    }
    public List<AnalistaDado> findAllAnalistas() {
        return em.createQuery("SELECT a FROM AnalistaDado a").getResultList();
    }

}
