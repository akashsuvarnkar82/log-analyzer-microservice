package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LogController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String pythonUrl = "http://127.0.0.1:8000/analyze";
    private final List<Map<String, Object>> logHistory = Collections.synchronizedList(new ArrayList<>());

    private final String[] possibleLogs = {
            "User 'admin' logged in successfully",
            "Successfully login attempt from IP 192.168.1.50",
            "Database backup completed",
            "Multiple failed password attempts on user 'root'",
            "File 'config.php' was modified by unknown user"
    };

    @Scheduled(fixedRate = 5000)
    public void autoSendLogs() {
        String randomLog = possibleLogs[new Random().nextInt(possibleLogs.length)];
        Map<String, String> data = new HashMap<>();
        data.put("content", randomLog);

        try {
            Map<String, Object> response = restTemplate.postForObject(pythonUrl, data, Map.class);

            // ADD THIS LINE: Capture the current time
            response.put("timestamp", new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date()));

            logHistory.add(response);
            if (logHistory.size() > 10)
                logHistory.remove(0);
            System.out.println("LOG STORED: " + randomLog);
        } catch (Exception e) {
            System.out.println("Python is offline...");
        }
    }

    @GetMapping("/api/logs")
    public List<Map<String, Object>> getLogs() {
        if (logHistory.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("analysis", "SYSTEM: Waiting for Data");
            error.put("content", "Ensure Python Brain is running at port 8000...");
            error.put("timestamp", "--:--:--");
            return List.of(error);
        }
        return logHistory;
    }
}