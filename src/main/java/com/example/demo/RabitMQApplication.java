package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan({"springfox", "com.example.demo"})
@EnableAsync
@SpringBootApplication
public class RabitMQApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabitMQApplication.class, args);
    }
}
