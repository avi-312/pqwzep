package com.pqwz.pqwzep.controller;

import com.pqwz.pqwzep.model.Employee;
import com.pqwz.pqwzep.repository.EmployeeRepository;
import com.pqwz.pqwzep.service.EmployeeAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final EmployeeAuthService employeeAuthService;
    private final EmployeeRepository employeeRepository;

    // POST: Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginRequest) {
        System.out.println("📩 /login endpoint hit!");
        String name = loginRequest.get("name");
        String password = loginRequest.get("password");

        boolean success = employeeAuthService.login(name, password);

        if (success) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid Name or Password");
        }
    }

    // GET: Fetch All Employees (for admin or debugging purpose)
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return ResponseEntity.ok(employees);
    }
}
