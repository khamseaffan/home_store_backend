package com.khamse.homestore.product.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient; // Correct import for Firebase StorageClient

@Configuration
@ConditionalOnProperty(name = "firebase.enabled", havingValue = "true", matchIfMissing = true) // matchIfMissing = true was already there, kept it as is.
public class FirebaseConfig {

    @Value("${firebase.credentials.location}")
    private String credentialResourcePath;

    
    @Bean(name = "firebaseCredentials")
    public GoogleCredentials googleCredentials() throws IOException {
        ClassPathResource resource = new ClassPathResource(credentialResourcePath);
        if (!resource.exists()) {
            throw new IOException("Firebase credentials file not found at: " + credentialResourcePath);
        }
        try (InputStream serviceAccount = resource.getInputStream()) {
            return GoogleCredentials.fromStream(serviceAccount);
        }
    }

    @Bean(name = "firebaseApp")
    public FirebaseApp firebaseApp(GoogleCredentials credentials) {
        // Check if the FirebaseApp is already initialized
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();
                
            return FirebaseApp.initializeApp(options);
        } else {
            return FirebaseApp.getInstance();
        }
    }

    @Bean // Bean for Firebase StorageClient
    @ConditionalOnProperty(name = "firebase.enabled", havingValue = "true")
    public StorageClient firebaseStorageClient(FirebaseApp firebaseApp) {
        return StorageClient.getInstance(firebaseApp);
    }
}