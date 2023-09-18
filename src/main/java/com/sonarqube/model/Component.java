package com.sonarqube.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Component {
    private String key;
    private String name;
    private String qualifier;
    private String visibility;

    // Constructors, getters, and setters
}
