package com.pqwz.pqwzep.controller;

import com.pqwz.pqwzep.model.Employee;
import com.pqwz.pqwzep.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/{id}/manager")
    public ResponseEntity<?> getManagerName(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(employee -> ResponseEntity.ok(Map.of("name", employee.getManagerName())))
                .orElse(ResponseEntity.notFound().build());
    }
}
