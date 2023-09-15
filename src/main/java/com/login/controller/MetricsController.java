package com.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.login.services.SonarQubeMetricsService;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {
    private final SonarQubeMetricsService sonarQubeMetricsService;

    @Autowired
    public MetricsController(SonarQubeMetricsService sonarQubeMetricsService) {
        this.sonarQubeMetricsService = sonarQubeMetricsService;
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchMetrics(@RequestParam String metricKey) {
        try {
            String metrics = sonarQubeMetricsService.getMetrics(metricKey);
            return new ResponseEntity<>(metrics, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to fetch metrics", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}