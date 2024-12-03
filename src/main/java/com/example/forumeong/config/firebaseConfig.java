package com.example.forumeong.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class firebaseConfig {

    @Value("/etc/secrets/serviceAccountKey.json")
    private String firebasePath;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        try (FileInputStream serviceAccount = new FileInputStream(firebasePath)) {
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
            
            return FirebaseApp.initializeApp(options);
        }
    }
}
