package com.springlab.week02;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class Week02Application {
    private final JdbcTemplate jdbc;

    public Week02Application(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public static void main(String[] args) {
        SpringApplication.run(Week02Application.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(JdbcTemplate jdbc) {
        return args -> {
            jdbc.execute("CREATE TABLE students(id INT PRIMARY KEY, name VARCHAR(100), mark INT)");
            jdbc.update("INSERT INTO students VALUES (1,'Sample User',90)");
            jdbc.update("INSERT INTO students VALUES (2,'Rahul',85)");
        };
    }

    @GetMapping("/students")
    public List<Map<String,Object>> students() {
        return jdbc.queryForList("SELECT * FROM students ORDER BY id");
    }

    @GetMapping("/")
    public String home() {
        return "Spring Core + JDBC + MVC are working. Open /api/students";
    }
}

