package com.khamse.home_store.testController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khamse.home_store.controller.StoreController;
import com.khamse.home_store.model.Store;
import com.khamse.home_store.service.StoreService;

@WebMvcTest(StoreController.class) 
public class StoreControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetStoreById() throws Exception {
        Store store = new Store();
        store.setId(1L);
        store.setName("Laptop");

        when(storeService.getStoreById(1L)).thenReturn(store);

        mockMvc.perform(get("/api/v1/stores/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Laptop"));
    }

    @Test
    public void testGetAllStores() throws Exception {
        Store store1 = new Store();
        store1.setId(1L);
        store1.setName("Laptop");

        Store store2 = new Store();
        store2.setId(2L);
        store2.setName("Mobile");

        List<Store> stores = Arrays.asList(store1, store2);

        when(storeService.getAllStores()).thenReturn(stores);

        mockMvc.perform(get("/api/v1/stores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Mobile"));
    }

    @Test
    public void testAddStore() throws Exception {
        Store store = new Store();
        store.setId(1L);
        store.setName("Laptop");

        when(storeService.addStore(any(Store.class))).thenReturn(store);

        mockMvc.perform(post("/api/v1/stores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(store)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Laptop"));
    }

    @Test
    public void testUpdateStore() throws Exception {
        Store store = new Store();
        store.setId(1L);
        store.setName("Updated Laptop");

        when(storeService.updateStore(eq(1L), any(Store.class))).thenReturn(store);

        mockMvc.perform(put("/api/v1/stores/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(store)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Updated Laptop"));
    }

    @Test
    public void testDeleteStore() throws Exception {
        doNothing().when(storeService).deleteStore(1L);

        mockMvc.perform(delete("/api/v1/stores/{id}", 1L))
                .andExpect(status().isOk());

        verify(storeService, times(1)).deleteStore(1L);
    }
}