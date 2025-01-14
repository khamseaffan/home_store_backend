package com.khamse.home_store.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khamse.home_store.model.User;
import com.khamse.home_store.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1")
@Tag(name = "Products", description = "Product management APIs")
public class UserCotroller {
    
    private final UserService userService;
    
    // @Autowired
    public UserCotroller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @Operation(summary = "Get all users", description = "Get all users from the database")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Get user by id", description = "Get user by id from the database")
    public User getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    @Operation(summary = "Add a new user", description = "Add a new user to the database")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "Update a user", description = "Update a user in the database")
    public User updateUser(@RequestParam Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Delete a user", description = "Delete a user from the database")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }
    

    
}
