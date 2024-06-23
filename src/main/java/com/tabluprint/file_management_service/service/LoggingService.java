package com.tabluprint.file_management_service.service;

import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Payload.StringPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LoggingService {

    private final Logging logging;

    @Autowired
    public LoggingService(Logging logging) {
        this.logging = logging;
    }

    public void log(String message) {
        LogEntry entry = LogEntry
                .newBuilder(StringPayload.of(message))
                .setLogName("tabluprint")
                .setSeverity(com.google.cloud.logging.Severity.INFO)
                .build();
        logging.write(Collections.singleton(entry));
    }
}
