package com.example.kdtbe5_miniproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Kdtbe5MiniProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Kdtbe5MiniProjectApplication.class, args);
    }

}
