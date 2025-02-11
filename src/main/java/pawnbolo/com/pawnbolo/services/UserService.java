package pawnbolo.com.pawnbolo.services;

import org.springframework.stereotype.Service;
import pawnbolo.com.pawnbolo.models.User;
import pawnbolo.com.pawnbolo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void updateUser(Long id, User updatedUser) {
        userRepository.findById(id).ifPresent(user -> {
//            user.setName(updatedUser.getName()); // Update name
//            user.setEmail(updatedUser.getEmail()); // Update email
            userRepository.save(user); // Save changes
        });
    }
}
