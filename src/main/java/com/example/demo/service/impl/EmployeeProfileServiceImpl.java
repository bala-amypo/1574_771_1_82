package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository repo;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repo) {
        this.repo = repo;
    }

    public EmployeeProfile createEmployee(EmployeeProfile employee) {
        return repo.save(employee);
    }

    public EmployeeProfile getEmployeeById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    public List<EmployeeProfile> getAllEmployees() {
        return repo.findAll();
    }

    public Optional<EmployeeProfile> findByEmployeeId(String employeeId) {
        return repo.findByEmployeeId(employeeId);
    }

    public EmployeeProfile updateEmployeeStatus(Long id, boolean active) {
        EmployeeProfile e = getEmployeeById(id);
        e.setActive(active);
        return repo.save(e);
    }
}
