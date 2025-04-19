package com.khamse.homestore.product.config;

// import java.io.IOException;
// import java.io.InputStream;

// import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.core.io.ClassPathResource;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;

@Configuration
@ConditionalOnProperty(name = "firebase.enabled", havingValue = "true", matchIfMissing = true)
public class FirebaseConfig {

    // @Value("${gcp.firebase.credentials.location}")
    // private String credentialResourcePath;

    
    // @Bean(name = "firebaseCredentials")
    // public GoogleCredentials googleCredentials() throws IOException {
    //     try {
    //         ClassPathResource resource = new ClassPathResource(credentialResourcePath);
    //         if (resource.exists()) {
    //             try (InputStream serviceAccount = resource.getInputStream()) {
    //                 return GoogleCredentials.fromStream(serviceAccount);
    //             }
    //         } else {
    //             // Fallback to application default credentials if file is not found
    //             return GoogleCredentials.getApplicationDefault();
    //         }
    //     } catch (IOException e) {
    //         // Fallback to application default credentials if there's an error
    //         return GoogleCredentials.getApplicationDefault();
    //     }
    // }

    // @Bean(name = "firebaseApp")
    // public FirebaseApp firebaseApp(GoogleCredentials credentials) {
    //     // Check if the FirebaseApp is already initialized
    //     if (FirebaseApp.getApps().isEmpty()) {
    //         FirebaseOptions options = FirebaseOptions.builder()
    //             .setCredentials(credentials)
    //             .build();
                
    //         return FirebaseApp.initializeApp(options);
    //     } else {
    //         return FirebaseApp.getInstance();
    //     }
    // }
}