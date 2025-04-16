package Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Models.trabalhoprojeto.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Finds a user by username and password
    Optional<User> findByUsernameAndPassword(String username, String password);

    // Optional: find by username only (for registration or forgot-password features)
    Optional<User> findByUsername(String username);
}
