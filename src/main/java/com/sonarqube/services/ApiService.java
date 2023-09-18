package com.sonarqube.services;

import com.sonarqube.model.ApiResponse;
import com.sonarqube.model.Component;
import com.sonarqube.model.Paging;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ApiService {

    public ApiResponse getApiData() {
        Paging paging = new Paging(1, 100, 1);
        Component component = new Component("sonar_application", "sonar_application", "TRK", "public");

        return new ApiResponse(paging, Collections.singletonList(component));
    }
}

