package com.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.services.SonarQubeService;

@RestController
@RequestMapping("/sonarqube")
public class SonarQubeController { 

	 private final SonarQubeService sonarQubeService; 

	    @Autowired
	    public SonarQubeController(SonarQubeService sonarQubeService) {
	        this.sonarQubeService = sonarQubeService;
	    } 
	    
	    @PostMapping("/login")
	    public ResponseEntity<String> login() {
	        ResponseEntity<String> loginResponse = sonarQubeService.login(); 
	        // Check the response from SonarQube to determine if the login was successful
	        if (loginResponse.getStatusCode().is2xxSuccessful()) {
	            return ResponseEntity.ok("Login successful");
	        } else {
	            return ResponseEntity.status(loginResponse.getStatusCode()).body("Login failed");
	        }
	    } 

	    @GetMapping("/projects")
	    public String getAllProjects() {
	        String projectsResponse = sonarQubeService.getAllProjects(); 
	        // You can process the projectsResponse here as needed
	        // For example, you can return the list of projects to the client 
	        return projectsResponse;
	    }

	    @GetMapping("/component")
	    public ResponseEntity<String> getComponents( ) {
	        ResponseEntity<String> projectsResponse = sonarQubeService.getComponentMeasures(); 
	        // You can process the projectsResponse here as needed
	        // For example, you can return the list of projects to the client 
	        return projectsResponse;
	    }

	    @GetMapping("/issue")
	    public ResponseEntity<String> getIssues() {
	        ResponseEntity<String> projectsResponse = sonarQubeService.getAllIssues(); 
	        // You can process the projectsResponse here as needed

	        // For example, you can return the list of projects to the client 
	        return projectsResponse;
	    }
	    }
