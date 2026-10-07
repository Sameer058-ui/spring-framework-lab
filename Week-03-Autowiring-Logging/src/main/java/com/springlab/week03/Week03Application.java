package com.springlab.week03;

import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/employee")
public class Week03Application {
    private final EmployeeService employeeService;

    public Week03Application(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Week03Application.class, args);
    }

    @GetMapping
    public String getEmployee() {
        return employeeService.getEmployee();
    }

    @Component
    static class Employee {
        String getDetails() {
            return "Employee: Sample User | Role: AIML Student";
        }
    }

    @Service
    static class EmployeeService {
        private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
        private final Employee employee;

        EmployeeService(Employee employee) {
            this.employee = employee;
            log.info("Employee injected into EmployeeService");
        }

        String getEmployee() {
            log.info("getEmployee() called");
            return employee.getDetails();
        }
    }
}

