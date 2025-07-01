package com.aetherial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiStrategyFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiStrategyFactoryApplication.class, args);
        "AiStrategyFactoryApplication has started successfully!".lines().forEach(System.out::println);
    }

}
