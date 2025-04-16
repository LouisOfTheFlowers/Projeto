package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Models.trabalhoprojeto.User;
import Repositorios.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
    public String getUserRole(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.map(User::getRole).orElse(null); // Return role or null if user doesn't exist
    }
}
