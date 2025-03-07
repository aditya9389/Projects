package com.crud.fnpblog.fcmNotification;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FireBaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        System.out.println("------------into FireBaseconfig to initialize firebase with firebase admin sdk----------");
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/fnp-blog-dac13-firebase-adminsdk-fbsvc-32442fc836.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);

    }
}
