package com.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.login.controller.SonarQubeController;

@SpringBootApplication
public class Sonarqube3Application {

	public static void main(String[] args) {
		SpringApplication.run(Sonarqube3Application.class, args);
	}
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SonarQubeController sonarQubeController() {
        return new SonarQubeController();
    }

}
