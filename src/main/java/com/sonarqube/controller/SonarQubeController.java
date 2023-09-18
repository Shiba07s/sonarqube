package com.sonarqube.controller;


import com.sonarqube.model.Project;
import com.sonarqube.model.SonarQubeComponent;
import com.sonarqube.services.SonarQubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

@RestController
public class SonarQubeController {
    private final SonarQubeService sonarQubeService;


    @Autowired
    private RestTemplate restTemplate;

    public SonarQubeController(SonarQubeService sonarQubeService) {
        this.sonarQubeService = sonarQubeService;
    }
    private final String sonarApiUrl = "http://51.20.63.250:9000/projects";

    @GetMapping("/projects/search")
    public ResponseEntity<Project[]> searchProjects() {
        // Create a HttpHeaders object and set the Authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:sonar".getBytes()));

        // Create a HttpEntity object with the HttpHeaders object and the Object body that you want to send in the request
        HttpEntity<?> entity = new HttpEntity<>(headers);

        // Call the RestTemplate.exchange() method with the HttpEntity object and the request method and URL
        ResponseEntity<Project[]> response = restTemplate.exchange(
                "http://51.20.63.250:9000/api/projects/search",
                HttpMethod.GET,
                entity,
                Project[].class
        );

        // Check the response status code
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to retrieve projects from SonarQube");
        }

        // Return the list of projects
        return response;
    }


    @GetMapping("/all-projects")
    public ResponseEntity<String> fetchRepository()
            {

        try {
            // You can configure RestTemplate to use Bearer token authentication here
            RestTemplate restTemplate = new RestTemplate();
            String accessToken = "34c4ad2a1679aaf82c330ae45ce61cdc6aaff43f"; // Replace with your GitHub access token
            //String apiUrl = sonarApiUrl ;

            ResponseEntity<String> response = restTemplate.getForEntity(sonarApiUrl, String.class);
            return response;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching repository data.");
        }
    }

    @GetMapping("/search-components")
    public List<SonarQubeComponent> searchComponents(@RequestParam String query) {
        return sonarQubeService.searchComponents(query);
    }
    @GetMapping("/search-projects")
    public ResponseEntity<String> searchProjects(@RequestParam String query) {
        return sonarQubeService.searchProjects(query);
    }

}