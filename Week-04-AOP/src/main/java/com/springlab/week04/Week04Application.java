package com.springlab.week04;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/student")
public class Week04Application {
    private final StudentService service;

    public Week04Application(StudentService service) { this.service = service; }

    public static void main(String[] args) {
        SpringApplication.run(Week04Application.class, args);
    }

    @GetMapping
    public String getStudent() { return service.getStudent(); }

    @PostMapping("/save")
    public String saveStudent() { return service.saveStudent(); }

    @Component
    static class Student {
        String details() { return "Student: Sample User | Course: AIML"; }
    }

    @Service
    static class StudentService {
        private final Student student;
        StudentService(Student student) { this.student = student; }
        String getStudent() { return student.details(); }
        String saveStudent() { return "Student saved successfully"; }
    }

    @Aspect
    @Component
    static class LoggingAspect {
        @Before("execution(* com.springlab.week04.Week04Application.StudentService.*(..))")
        public void beforeMethod(JoinPoint jp) {
            System.out.println("AOP @Before -> " + jp.getSignature().getName());
        }
    }
}

