package com.sonarqube.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.*;

@Service
public class SonarServiceImpl implements SonarService{

    private final RestTemplate restTemplate;

    public SonarServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String sonarToken = "34c4ad2a1679aaf82c330ae45ce61cdc6aaff43f";

    public List<Map> getAllProjects() {
    String sonarToken = "34c4ad2a1679aaf82c330ae45ce61cdc6aaff43f";

    // Create a HttpHeaders object
    HttpHeaders headers = new HttpHeaders();

    // Set the Authorization header to the value of your token
    headers.set("Authorization", "Bearer " + sonarToken);

    // Create a HttpEntity object with the HttpHeaders object and the Object body that you want to send in the request
    HttpEntity<?> entity = new HttpEntity<>(headers);

    // Call the RestTemplate.exchange() method with the HttpEntity object and the request method and URL
    ResponseEntity<Map[]> response = restTemplate.exchange(
            "http://51.20.63.250:9000/api/components/search?qualifiers=TRK",
            HttpMethod.GET,
            entity,
            Map[].class
    );

    // Check the response status code
    if (response.getStatusCode() != HttpStatus.OK) {
        throw new RuntimeException("Failed to retrieve projects from SonarQube");
    }

    // Return the list of projects
    return Arrays.asList(response.getBody());
}


//    RestTemplate restTemplate2 = new RestTemplateBuilder()
//            .defaultHeader("Authorization", "Bearer " + sonarToken)
//            .build();
//        public static List<Map> getAllProjects(RestTemplate restTemplate) {
//        // Call the RestTemplate.exchange() method with the request method and URL
//        ResponseEntity<Map[]> response = restTemplate.exchange(
//                "https://your-sonarqube-server/api/components/search?qualifiers=TRK",
//                HttpMethod.GET,
//                Map[].class
//        );
//
//        // Check the response status code
//        if (response.getStatusCode() != HttpStatus.OK) {
//            throw new RuntimeException("Failed to retrieve projects from SonarQube");
//        }
//
//        // Return the list of projects
//        return Arrays.asList(response.getBody());
//    }

//    @Override
//    public List<Map> getAllProjects() {
//        ResponseEntity<Map[]> response;
//        response = restTemplate.exchange(
//                "https://your-sonarqube-server/api/components/search?qualifiers=TRK",
//                GET,
//                Map[].class
//        );
//
//        // Check the response status code
//        if (response.getStatusCode() != HttpStatus.OK) {
//            throw new RuntimeException("Failed to retrieve projects from SonarQube");
//        }
//
//        // Return the list of projects
//        return Arrays.asList(response.getBody());
//    }


//    public static List<Map> getAllProjects(RestTemplate restTemplate) {
//        // Call the RestTemplate.exchange() method with the request method and URL
//        ResponseEntity<Map[]> response = restTemplate.exchange(
//                "https://your-sonarqube-server/api/components/search?qualifiers=TRK",
//                HttpMethod.GET,
//                Map[].class
//        );
//
//        // Check the response status code
//        if (response.getStatusCode() != HttpStatus.OK) {
//            throw new RuntimeException("Failed to retrieve projects from SonarQube");
//        }
//
//        // Return the list of projects
//        return Arrays.asList(response.getBody());
//    }



//    public List<Map> getAllProjects() {
//        // Create a HttpHeaders object
//        HttpHeaders headers = new HttpHeaders();
//
//        // Set the Authorization header to the value of your token
//        headers.set("Authorization", "Bearer " + "yourToken");
//
//        // Create a HttpEntity object with the HttpHeaders object and the Object body that you want to send in the request
//        HttpEntity<?> entity = new HttpEntity<>(headers);
//
//        // Call the RestTemplate.exchange() method with the HttpEntity object and the request method and URL
//        ResponseEntity<Map[]> response = restTemplate.exchange(
//                "http://51.20.63.250:9000/api/components/search?qualifiers=TRK",
//                HttpMethod.GET,
//                entity,
//                Map[].class
//        );
//
//        // Check the response status code
//        if (response.getStatusCode() != HttpStatus.OK) {
//            throw new RuntimeException("Failed to retrieve projects from SonarQube");
//        }
//
//        // Return the list of projects
//        return Arrays.asList(response.getBody());
//    }

  //  @Override
//    public List<Map> getAllProjects() {
//        // Create a request to the SonarQube REST API
//        ResponseEntity<Map[]> response = restTemplate.getForEntity(
//                "http://51.20.63.250:9000/api/components/search?qualifiers=TRK",
//                Map[].class
//        );
//
//        // Check the response status code
//        if (response.getStatusCode() != HttpStatus.OK) {
//            throw new RuntimeException("Failed to retrieve projects from SonarQube");
//        }
//
//        // Return the list of projects
//        return Arrays.asList(response.getBody());
//    }
}

