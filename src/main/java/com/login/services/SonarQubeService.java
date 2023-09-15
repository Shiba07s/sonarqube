package com.login.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SonarQubeService {
	
	private final RestTemplate restTemplate;
    private final String sonarQubeUrl;
    private final String apiToken;
	private String sonarqubeUrl1;
	
    @Autowired
    public SonarQubeService(
        RestTemplate restTemplate,
        @Value("${sonarqube.url}") String sonarQubeUrl,
        @Value("${sonarqube.url1}")String sonarqubeUrl1,
        @Value("${sonarqube.apiToken}") String apiToken)

    {

        this.restTemplate = restTemplate;
        this.sonarQubeUrl = sonarQubeUrl;
        this.sonarqubeUrl1 = sonarqubeUrl1;
        this.apiToken = apiToken;
    } 
    public ResponseEntity<String> login() { 
        HttpHeaders headers = new HttpHeaders(); 
        headers.set("Authorization", "Bearer " + apiToken); 
        String loginUrl = sonarQubeUrl + "/api/authentication/login"; 
        HttpEntity<String> requestEntity = new HttpEntity<>(headers); 
        ResponseEntity<String> response = restTemplate.exchange(loginUrl, HttpMethod.POST, requestEntity, String.class); 
        return response;
    } 

    public String getAllProjects() {

    	 HttpHeaders headers = new HttpHeaders();

    	    headers.set("Authorization", "Bearer " + apiToken);

    	    headers.set("Accept", "application/json");

    	    headers.set("Content-Type", "application/json"); 
    	    String projectsUrl ="http://13.234.23.179:9000/projects?sort=webappExample Maven Webapp"

    	    		+ "Passed"; 
    	    HttpEntity<String> requestEntity = new HttpEntity<>(headers); 
    	    ResponseEntity<String> response = restTemplate.exchange(projectsUrl, HttpMethod.GET, requestEntity, String.class); 
    	    // Log the response for debugging

    	    System.out.println("Response Status Code: " + response.getStatusCode());
    	    System.out.println("Response Body: " + response.getBody());
    	    return response.getBody();

    } 
    public ResponseEntity<String> getComponentMeasures( ) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        headers.set("Content-Type", "application/json"); 
       try {

    	   String measuresUrl ="http://13.234.23.179:9000/api/metrics/search?id=random1"; 
           HttpEntity<String> requestEntity = new HttpEntity<>(headers); 
           ResponseEntity<String> response = restTemplate.exchange(measuresUrl, HttpMethod.GET, requestEntity, String.class);
           return response;
       }catch(Exception e) {

    	   e.printStackTrace(); 
       }

	return null; 
    } 
    public ResponseEntity<String> getAllIssues() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        headers.set("Accept", "application/json");
	    headers.set("Content-Type", "application/json");

        // Customize this URL based on your SonarQube server's API endpoint for listing issues

        String issuesUrl = sonarQubeUrl + "/api/issues/search"; 
        HttpEntity<String> requestEntity = new HttpEntity<>(headers); 
        // Send the request to get all issues

        ResponseEntity<String> response = restTemplate.exchange(issuesUrl, HttpMethod.GET, requestEntity, String.class);
        System.out.println("Response Status Code: " + response.getStatusCode());
	    System.out.println("Response Body: " + response.getBody()); 
        return response;
    }
}

 
