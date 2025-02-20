package com.example.springdbapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(YamlDataSourcesProperties.class)
public class SpringDbAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDbAppApplication.class, args);
    }

}
