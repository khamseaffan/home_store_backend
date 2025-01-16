package com.khamse.home_store.testService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.khamse.home_store.model.User;
import com.khamse.home_store.repository.UserRepository;
import com.khamse.home_store.service.UserService;

@SpringBootTest
public class UserServiceTests {

    @MockBean
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    @Test
    public void testGetUserById() {
        User user = new User("John", "Doe", "john.doe@example.com", "password", 
                    "USER", "1234567890", "123 Main St", "Apt 4", "Springfield", 
                    "12345", "IL", "USA");
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        User foundUser = userService.getUserById(1L);

        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getFirstName(), foundUser.getFirstName());
        assertEquals(user.getLastName(), foundUser.getLastName());
        assertEquals(user.getEmail(), foundUser.getEmail());
    }

    @Test
    public void testAddUser(){
        User user = new User("John", "Doe", "john.doe@example.com", "password", 
                    "USER", "1234567890", "123 Main St", "Apt 4", "Springfield", 
                    "12345", "IL", "USA");
        user.setId(1L);

        when(userRepository.save(user)).thenReturn(user);

        User addedUser = userService.addUser(user);

        assertEquals(user.getId(), addedUser.getId());
        assertEquals(user.getFirstName(), addedUser.getFirstName());
        assertEquals(user.getLastName(), addedUser.getLastName());
        assertEquals(user.getEmail(), addedUser.getEmail());
    }

    @Test
    public void testUpdateUser(){
        User user = new User("John", "Doe", "john.doe@example.com", "password", 
                    "USER", "1234567890", "123 Main St", "Apt 4", "Springfield", 
                    "12345", "IL", "USA");
        user.setId(1L);

        User newUserDetails = new User("John", "Doe", "doe.john@example.com", "password", 
                    "USER", "12277337890", "123 Main St", "Apt 4", "Springfield", 
                    "12345", "IL", "USA");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateUser(1L, newUserDetails);

        assertEquals(user.getId(), updatedUser.getId());
        assertEquals(newUserDetails.getFirstName(), updatedUser.getFirstName());
        assertEquals(newUserDetails.getEmail(), updatedUser.getEmail());
        assertEquals(newUserDetails.getPhoneNumber(), updatedUser.getPhoneNumber());
        
    }

    @Test
    public void testDeleteUser(){
        User user = new User("John", "Doe", "john.doe@example.com", "password", 
                    "USER", "1234567890", "123 Main St", "Apt 4", "Springfield", 
                    "12345", "IL", "USA");
        user.setId(1L);

        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        userService.deleteUser(1L);
    }

    @Test
    public void testGetAllUsers(){
        User user1 = new User("John", "Doe", "john.doe@example.com", "password", 
                    "USER", "1234567890", "123 Main St", "Apt 4", "Springfield", 
                    "12345", "IL", "USA");
        user1.setId(1L);

        User user2 = new User("Jane", "Doe", "jane.doe@example.com", "password", 
                            "USER", "0987654321", "456 Elm St", "Apt 5", "Springfield", 
                            "12345", "IL", "USA");
        user2.setId(2L);

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> allUsers = userService.getAllUsers();

        assertEquals(2, allUsers.size());
        assertEquals(user1.getId(), allUsers.get(0).getId());
        assertEquals(user2.getId(), allUsers.get(1).getId());
        assertEquals(user1.getFirstName(), allUsers.get(0).getFirstName());
        assertEquals(user2.getFirstName(), allUsers.get(1).getFirstName());
        
    }
}
