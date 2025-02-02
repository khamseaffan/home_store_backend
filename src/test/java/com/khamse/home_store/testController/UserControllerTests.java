package com.khamse.home_store.testController;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khamse.home_store.controller.UserController;
import com.khamse.home_store.model.User;
import com.khamse.home_store.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User(1L, "John", "Doe", "johndoe@example.com", "password", "USER", "1234567890", "123 Main St", "Apt 1", "Springfield", "12345", "IL", "USA");
        User user2 = new User(2L, "Jane", "Doe", "janedoe@example.com", "password", "USER", "1234567890", "123 Main St", "Apt 1", "Springfield", "12345", "IL", "USA");
        List<User> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Doe"));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User(1L, "John", "Doe", "johndoe@example.com", "password", "USER", "1234567890", "123 Main St", "Apt 1", "Springfield", "12345", "IL", "USA");

        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/v1/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User(1L, "John", "Doe", "johndoe@example.com", "password", "USER", "1234567890", "123 Main St", "Apt 1", "Springfield", "12345", "IL", "USA");
        User savedUser = new User(1L, "John", "Doe", "johndoe@example.com", "password", "USER", "1234567890", "123 Main St", "Apt 1", "Springfield", "12345", "IL", "USA");
    
        when(userService.addUser(any(User.class))).thenReturn(savedUser);
    

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User updatedUser = new User(1L, "John", "Updated", "johndoe@example.com", "password", "USER", "1234567890", "140 Main St", "Apt 1", "Springfield", "12345", "IL", "USA");

        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(updatedUser);


        mockMvc.perform(put("/api/v1/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Updated"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/v1/users/{id}", 1L))
                .andExpect(status().isOk());
    }
}