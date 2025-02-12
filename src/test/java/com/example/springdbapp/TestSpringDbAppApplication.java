package com.example.springdbapp;

import org.springframework.boot.SpringApplication;

public class TestSpringDbAppApplication {

    public static void main(String[] args) {
        SpringApplication.from(SpringDbAppApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
