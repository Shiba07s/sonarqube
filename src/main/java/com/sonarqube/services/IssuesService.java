package com.sonarqube.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IssuesService {

    private final String SONARQUBE_API_URL = "http://51.20.63.250:9000/api/issues/search";

    public String searchIssues() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(SONARQUBE_API_URL, String.class);
    }
}

