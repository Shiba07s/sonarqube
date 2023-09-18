package com.sonarqube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Controller
public class IssuesController {

    private final String SONARQUBE_API_URL = "http://51.20.63.250:9000/api/issues/search";

    private final RestTemplate restTemplate;

    @Autowired
    public IssuesController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchIssues() {
        try {
            // Make a GET request to the SonarQube API
            String response = restTemplate.getForObject(SONARQUBE_API_URL, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle exceptions, log errors, and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching data from the SonarQube API.");
        }
    }
}

