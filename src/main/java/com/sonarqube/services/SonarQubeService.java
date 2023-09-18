package com.sonarqube.services;

import com.sonarqube.model.SonarQubeComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class SonarQubeService {
    @Value("${sonarqube.api.url}")
    private String sonarQubeApiUrl; // Define this in your application.properties

    private final RestTemplate restTemplate;

    public SonarQubeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<SonarQubeComponent> searchComponents(String query) {
        String apiUrl = sonarQubeApiUrl + "/api/components/search?q=" + query;
        ResponseEntity<List<SonarQubeComponent>> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SonarQubeComponent>>() {}
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            // Handle errors appropriately
            return Collections.emptyList();
        }
    }
    public ResponseEntity<String> searchProjects(String query) {
        String apiUrl = sonarQubeApiUrl + "/api/projects/search?q=" + query;
        return restTemplate.getForEntity(apiUrl, String.class);
    }
}
