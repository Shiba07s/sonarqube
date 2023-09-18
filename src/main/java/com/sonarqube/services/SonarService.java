package com.sonarqube.services;

import com.sonarqube.model.Component;

import java.util.List;
import java.util.Map;

public interface SonarService {
    List<Map> getAllProjects();
}
