package com.sonarqube.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Paging {
    private int pageIndex;
    private int pageSize;
    private int total;

    // Constructors, getters, and setters
}
