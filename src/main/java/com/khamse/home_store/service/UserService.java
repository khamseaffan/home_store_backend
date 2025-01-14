package com.khamse.home_store.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.khamse.home_store.model.User;
import com.khamse.home_store.repository.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    // @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {

        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        return userRepository.findById(id).map(currentUserDetails -> {
            currentUserDetails.setFirstName(user.getFirstName());
            currentUserDetails.setLastName(user.getLastName());
            currentUserDetails.setEmail(user.getEmail());
            currentUserDetails.setPhoneNumber(user.getPhoneNumber());
            currentUserDetails.setAddress(user.getAddress());
            return userRepository.save(currentUserDetails);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    
    
}
