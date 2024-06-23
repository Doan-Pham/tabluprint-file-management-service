package com.tabluprint.file_management_service.config;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
public class LoggingConfig {

    @Bean
    public Logging googleCloudLogging() throws IOException {
        return LoggingOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("./tabluprint-242d321299b4.json")))
                .build().getService();
    }
}
