package com.springlab.week01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Week01Application {
    public static void main(String[] args) {
        SpringApplication.run(Week01Application.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World from Spring Framework!";
    }
}

