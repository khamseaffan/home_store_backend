package com.khamse.homestore.product.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.khamse.homestore.product.util.FirebaseStorageService;

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
}