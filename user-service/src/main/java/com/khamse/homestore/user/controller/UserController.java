package com.khamse.homestore.user.controller; // Updated package

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khamse.homestore.common.dto.UserRequestDTO; // Updated import
import com.khamse.homestore.common.dto.UserResponseDTO; // Updated import
import com.khamse.homestore.user.service.UserService; // Updated import

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1")
@Tag(name = "Users", description = "Product management APIs")
public class UserController {
    
    private final UserService userService;
    
    // @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @Operation(summary = "Get all users", description = "Get all users from the database")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Get user by id", description = "Get user by id from the database")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping(path = {"/users", "/users/{id}"} )
    @Operation(summary = "Add a new user", description = "Add a new store to the database")
    public ResponseEntity<UserResponseDTO> saveProduct(
        @PathVariable(value = "id", required = false) UUID userId, 
        @RequestBody UserRequestDTO userDTO) {
        return ResponseEntity.ok(userService.addOrUpdateUser(userId, userDTO));
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Delete a user", description = "Delete a user from the database")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    
}
