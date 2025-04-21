package com.khamse.homestore.product.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.khamse.homestore.product.util.FirebaseStorageService;
import com.khamse.homestore.product.repository.GlobalProductRepository;
import com.khamse.homestore.product.repository.StoreProductRepository;

@TestConfiguration
@Profile("test")
public class ProductTestConfiguration {

    @Bean
    @Primary
    public GoogleCredentials mockGoogleCredentials() {
        return Mockito.mock(GoogleCredentials.class);
    }
    
    @Bean
    @Primary
    public FirebaseApp mockFirebaseApp() {
        return Mockito.mock(FirebaseApp.class);
    }
    
    @Bean
    @Primary
    public FirebaseStorageService mockFirebaseStorageService() {
        return Mockito.mock(FirebaseStorageService.class);
    }
    
    /*
    @Bean
    @Primary
    public GlobalProductRepository mockGlobalProductRepository() {
        return Mockito.mock(GlobalProductRepository.class);
    }
    
    @Bean
    @Primary
    public StoreProductRepository mockStoreProductRepository() {
        return Mockito.mock(StoreProductRepository.class);
    }
    */
}