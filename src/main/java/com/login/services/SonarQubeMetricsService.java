package com.login.services;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SonarQubeMetricsService {
    private final RestTemplate restTemplate;
    private final String sonarQubeApiUrl;
    private final String sonarQubeApiToken;

    @Autowired
    public SonarQubeMetricsService(
            @Value("${sonarqube.api.url}") String sonarQubeApiUrl,
            @Value("${sonarqube.api.token}") String sonarQubeApiToken) {
        this.sonarQubeApiUrl = sonarQubeApiUrl;
        this.sonarQubeApiToken = sonarQubeApiToken;
        this.restTemplate = new RestTemplate();
    }

    public String getMetrics(String metricKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + sonarQubeApiToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
            sonarQubeApiUrl + "/metrics/search?key=" + metricKey,
            HttpMethod.GET,
            entity,
            String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch metrics from SonarQube");
        }
    }
}

