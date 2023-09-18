package com.sonarqube.controller;

import com.sonarqube.model.Component;
import com.sonarqube.services.SonarService;
import com.sonarqube.services.SonarServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SonarController {

    private final SonarServiceImpl sonarService;

    public SonarController(SonarServiceImpl sonarService) {
        this.sonarService = sonarService;
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Map>> getAllProjects() {
        List<Map> projects = sonarService.getAllProjects();
        return ResponseEntity.ok(projects);
    }
}

