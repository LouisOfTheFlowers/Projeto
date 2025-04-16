package Models.trabalhoprojeto;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // "AGRICULTOR", "GESTOR", or "ANALISTA"

    // Getters and Setters

    public String getRole() {
        return role;
    }

    // Other getters and setters as necessary
}
