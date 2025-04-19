package com.khamse.homestore.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import com.khamse.homestore.common.dto.UserRequestDTO;
import com.khamse.homestore.common.dto.UserResponseDTO;
import com.khamse.homestore.common.model.User;
import com.khamse.homestore.user.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService{

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    
    private final UserRepository userRepository;

    // @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> userList = new ArrayList<>();
        for( User user: userRepository.findAll() ){
            userList.add(new UserResponseDTO(
                user.getId(), user.getFirstName(),user.getLastName(), user.getEmail(), user.getPhoneNumber(), 
                user.getStreetAddress(), user.getCity(), user.getState(),user.getZipcode(), user.getCountry(),
                user.getRole()
            ));
        }

        return userList;
    }

    public UserResponseDTO getUserById(UUID userId) {

        return userRepository.findById(userId)
        .map(fetchedStore -> {
                return convertToResponseDTO(fetchedStore);
            })
        .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + userId));

    }


    @Transactional
     public UserResponseDTO addOrUpdateUser(UUID userId, UserRequestDTO userDTO) {
        if (userId != null) {
            return userRepository.findById(userId)
            .map(existingUser -> {
                existingUser.setFirstName(userDTO.getFirstName());
                existingUser.setLastName(userDTO.getLastName());
                existingUser.setStreetAddress(userDTO.getStreetAddress());
                existingUser.setCity(userDTO.getCity());
                existingUser.setState(userDTO.getState());
                existingUser.setZipcode(userDTO.getZipcode());
                existingUser.setEmail(userDTO.getEmail());
                existingUser.setPhoneNumber(userDTO.getPhoneNumber());
                
    
                return convertToResponseDTO(existingUser);
            })
            .orElseThrow(() ->  new EntityNotFoundException("User not found with id: " + userId));
        }
        
        User newUser = new User(
                userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getUserRole(),
                userDTO.getPhoneNumber(), userDTO.getStreetAddress(), userDTO.getCity(), userDTO.getZipcode(), userDTO.getState(),
                userDTO.getCountry());
        System.out.println("\n\n" + newUser.getId().toString() +"\n\n");
        return convertToResponseDTO(userRepository.save(newUser));
    }


    @Transactional
    public boolean deleteUser(UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public UserResponseDTO convertToResponseDTO(User user){
        UserResponseDTO userResponseDTO = new UserResponseDTO(user.getId(),user.getFirstName(), user.getLastName(), user.getEmail(),
                                                            user.getRole(), user.getPhoneNumber());    
        if(user.getStreetAddress() != null)
            userResponseDTO.setStreetAddress(user.getStreetAddress());
        if(user.getCity() != null)
            userResponseDTO.setCity(user.getCity());
        if(user.getState() != null)
            userResponseDTO.setState(user.getState());
        if(user.getZipcode() != null)
            userResponseDTO.setZipcode(user.getZipcode());
        if(user.getCountry() != null)
            userResponseDTO.setCountry(user.getCountry());

        LOGGER.info("Converted user:\n" + user.getId());
        return userResponseDTO;

        }

    
    
}
