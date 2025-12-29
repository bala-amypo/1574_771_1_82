package com.example.demo.controller;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeService;

    public EmployeeProfileController(EmployeeProfileService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeProfile> create(
            @RequestBody EmployeeProfile employee) {
        return ResponseEntity.ok(
                employeeService.createEmployee(employee)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfile> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<EmployeeProfile>> getAll() {
        return ResponseEntity.ok(
                employeeService.getAllEmployees()
        );
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<EmployeeProfile> updateStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        return ResponseEntity.ok(
                employeeService.updateEmployeeStatus(id, active)
        );
    }
}
