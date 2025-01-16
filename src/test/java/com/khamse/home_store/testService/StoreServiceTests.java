package com.khamse.home_store.testService;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.khamse.home_store.model.Store;
import com.khamse.home_store.repository.StoreRepository;
import com.khamse.home_store.service.StoreService;

@SpringBootTest
public class StoreServiceTests {
    
    @MockBean
    private StoreRepository storeRepository;
    
    @Autowired
    private StoreService storeService;

    @Test
    public void testGetStoreById() {
        Store store = new Store();
        store.setId(1L);
        store.setName("Taaza");
        store.setAddress("1234 Main St");
        store.setPhone("123-456-7890");
        store.setEmail("taazaa@gmail.com");
        store.setStoreType("Grocery");

        Mockito.when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        Store fetchedStore = storeService.getStoreById(1L);

        Assertions.assertEquals(fetchedStore.getId(), 1L);
        Assertions.assertEquals(fetchedStore.getName(), "Taaza");
        Assertions.assertEquals(fetchedStore.getAddress(), "1234 Main St");
        Assertions.assertEquals(fetchedStore.getPhone(), "123-456-7890");
        Assertions.assertEquals(fetchedStore.getEmail(), "taazaa@gmail.com");
        Assertions.assertEquals(fetchedStore.getStoreType(), "Grocery");
    }

    @Test
    public void testAddStore() {
        Store store = new Store();
        store.setId(1L);
        store.setName("Taaza");
        store.setAddress("1234 Main St");
        store.setPhone("123-456-7890");
        store.setEmail("taazaa@gmail.com");
        store.setStoreType("Grocery");

        Mockito.when(storeRepository.save(store)).thenReturn(store);

        Store addedStore = storeService.addStore(store);

        Assertions.assertEquals(addedStore.getId(), 1L);
        Assertions.assertEquals(addedStore.getName(), "Taaza");
        Assertions.assertEquals(addedStore.getAddress(), "1234 Main St");
        Assertions.assertEquals(addedStore.getPhone(), "123-456-7890");
        Assertions.assertEquals(addedStore.getEmail(), "taazaa@gmail.com");
        Assertions.assertEquals(addedStore.getStoreType(), "Grocery");
    }

    @Test
    public void testUpdateStore() {
        Store store = new Store();
        store.setId(1L);
        store.setName("Taaza");
        store.setAddress("1234 Main St");
        store.setPhone("123-456-7890");
        store.setEmail("taazaa@gmail.com");
        store.setStoreType("Grocery");

        Store store2 = new Store();
        store2.setId(1L);
        store2.setName("Taaza");
        store2.setAddress("1234 Main St");
        store2.setPhone("123-456-9870");
        store2.setEmail("taazaa@gmail.com");
        store2.setStoreType("Grocery");

        Mockito.when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        Mockito.when(storeRepository.save(store)).thenReturn(store);

        Store updatedStore = storeService.updateStore(1L, store2);

        Assertions.assertEquals(updatedStore.getId(), 1L);
        Assertions.assertEquals(updatedStore.getName(), "Taaza");
        Assertions.assertEquals(updatedStore.getAddress(), "1234 Main St");
        Assertions.assertEquals(updatedStore.getPhone(), "123-456-9870");
        Assertions.assertEquals(updatedStore.getEmail(), "taazaa@gmail.com");
        Assertions.assertEquals(updatedStore.getStoreType(), "Grocery");
    }

    @Test
    public void testDeleteStore() {
        Store store = new Store();
        store.setId(1L);
        store.setName("Taaza");
        store.setAddress("1234 Main St");
        store.setPhone("123-456-7890");
        store.setEmail("taazaa@gmail.com");
        store.setStoreType("Grocery");

        Mockito.when(storeRepository.existsById(1L)).thenReturn(true);
        Mockito.when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        Assertions.assertDoesNotThrow(() -> storeService.deleteStore(1L));

        Mockito.verify(storeRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testGetAllStores() {
        Store store = new Store();
        store.setId(1L);
        store.setName("Taaza");
        store.setAddress("1234 Main St");
        store.setPhone("123-456-7890");
        store.setEmail("taazaa@gmail.com");
        store.setStoreType("Grocery");

        Store store2 = new Store();
        store2.setId(2L);
        store2.setName("Uncle Steve");
        store2.setAddress("2232 Main St");
        store2.setPhone("123-646-2290");
        store2.setEmail("usteve@gamil.com");
        store2.setStoreType("Grocery");

        Mockito.when(storeService.getAllStores()).thenReturn(List.of(store, store2));
        List<Store> stores = storeService.getAllStores();

        Assertions.assertEquals(stores.size(), 2);
        Assertions.assertEquals(stores.get(0).getId(), 1L);
        Assertions.assertEquals(stores.get(0).getName(), "Taaza");
        Assertions.assertEquals(stores.get(0).getAddress(), "1234 Main St");
        Assertions.assertEquals(stores.get(0).getPhone(), "123-456-7890");
        Assertions.assertEquals(stores.get(0).getEmail(), "taazaa@gmail.com");
        Assertions.assertEquals(stores.get(0).getStoreType(), "Grocery");
    }
}
