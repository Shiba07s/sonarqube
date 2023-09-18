package com.sonarqube.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SonarQubeComponent {
    private String id;
    private String key;
    private String name;

    // getters and setters
}

