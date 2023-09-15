package com.login.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.login.model.ProjectSearchResponse;

@Service
public class ProjectService {

    private final RestTemplate restTemplate;

    @Value("${sonarqube.api.url}")
    private String sonarqubeApiUrl;

    @Value("${sonarqube.api.token}")
    private String sonarqubeApiToken;

    @Autowired
    public ProjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProjectSearchResponse searchProjects() {
        // Build the URL for the SonarQube API endpoint
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(sonarqubeApiUrl + "/api/projects/search")
                .queryParam("pageSize", 100);// You can customize other query parameters as needed

        // Set the authentication token in the headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + sonarqubeApiToken);

        // Create the HTTP request entity with headers
        HttpEntity<?> entity = new HttpEntity<>(headers);

        // Make the HTTP GET request
        ResponseEntity<ProjectSearchResponse> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                ProjectSearchResponse.class
        );

        // Extract and return the response body
        return responseEntity.getBody();
    }
}

