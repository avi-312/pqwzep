package com.pqwz.pqwzep.controller;

import com.pqwz.pqwzep.model.Employee;
import com.pqwz.pqwzep.repository.EmployeeRepository;
import com.pqwz.pqwzep.service.EmployeeAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final EmployeeAuthService employeeAuthService;
    private final EmployeeRepository employeeRepository;

    // POST: Login Endpoint (Modified to accept id and password)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginRequest) {
        System.out.println("📩 /login endpoint hit!");
        Long id = Long.parseLong(loginRequest.get("id"));
        String password = loginRequest.get("password");

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            return ResponseEntity.status(401).body("Incorrect ID");
        }

        boolean success = employeeAuthService.loginById(id, password);
        if (success) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Incorrect Password");
        }
    }

    // GET: Fetch All Employees (for admin or debugging purpose)
    @GetMapping("/login")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return ResponseEntity.ok(employees);
    }
}
