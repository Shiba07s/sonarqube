package com.login.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.login.model.Metric;
import com.login.services.SonarQubeService;

@RestController
public class SonarQubeController {

    private final SonarQubeService sonarQubeService;
    
//    public SonarClient(String sonarServerUrl) {
//        SonarClientBuilder sonarClientBuilder = new SonarClientBuilder();
//        sonarClientBuilder.url(sonarServerUrl);
//        sonarClient = sonarClientBuilder.build();
//    }

    public SonarQubeController(SonarQubeService sonarQubeService) {
        this.sonarQubeService = sonarQubeService;
    }

    @GetMapping("/api/metrics/search")
    public List<Metric> getMetrics(@RequestParam("metricKeys") String metricKeys) {
        return sonarQubeService.getMetrics(metricKeys);
    }
}