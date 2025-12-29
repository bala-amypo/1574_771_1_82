package com.example.demo.controller;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;

    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

    @PostMapping
    public ResponseEntity<EmployeeProfileDto> createEmployee(@RequestBody EmployeeProfileDto dto) {
        EmployeeProfile employee = new EmployeeProfile(dto.getEmployeeId(), dto.getFullName(), 
                dto.getEmail(), dto.getTeamName(), dto.getRoles());
        EmployeeProfile created = employeeProfileService.createEmployee(employee);
        return ResponseEntity.ok(toDto(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfileDto> getEmployee(@PathVariable Long id) {
        EmployeeProfile employee = employeeProfileService.getEmployeeById(id);
        return ResponseEntity.ok(toDto(employee));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeProfileDto>> getAllEmployees() {
        List<EmployeeProfile> employees = employeeProfileService.getAllEmployees();
        List<EmployeeProfileDto> dtos = employees.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<EmployeeProfileDto> updateEmployeeStatus(@PathVariable Long id, @RequestParam boolean active) {
        EmployeeProfile updated = employeeProfileService.updateEmployeeStatus(id, active);
        return ResponseEntity.ok(toDto(updated));
    }

    @GetMapping("/lookup/{employeeId}")
    public ResponseEntity<EmployeeProfileDto> lookupEmployee(@PathVariable String employeeId) {
        Optional<EmployeeProfile> employee = employeeProfileService.findByEmployeeId(employeeId);
        return employee.map(emp -> ResponseEntity.ok(toDto(emp)))
                .orElse(ResponseEntity.notFound().build());
    }

    private EmployeeProfileDto toDto(EmployeeProfile employee) {
        return new EmployeeProfileDto(employee.getId(), employee.getEmployeeId(), employee.getFullName(),
                employee.getEmail(), employee.getTeamName(), employee.getRoles(), employee.getActive(), employee.getCreatedAt());
    }
}






// package com.example.demo.controller;

// import com.example.demo.dto.EmployeeProfileDto;
// import com.example.demo.model.EmployeeProfile;
// import com.example.demo.service.EmployeeProfileService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/api/employees")
// public class EmployeeProfileController {

//     private final EmployeeProfileService employeeService;

//     public EmployeeProfileController(EmployeeProfileService employeeService) {
//         this.employeeService = employeeService;
//     }

//     @PostMapping
//     public ResponseEntity<EmployeeProfileDto> createEmployee(@RequestBody EmployeeProfileDto dto) {
//         EmployeeProfile entity = mapToEntity(dto);
//         EmployeeProfile saved = employeeService.createEmployee(entity);
//         return ResponseEntity.ok(mapToDto(saved));
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<EmployeeProfileDto> getEmployee(@PathVariable Long id) {
//         EmployeeProfile employee = employeeService.getEmployeeById(id);
//         return ResponseEntity.ok(mapToDto(employee));
//     }

//     @GetMapping
//     public ResponseEntity<List<EmployeeProfileDto>> getAllEmployees() {
//         List<EmployeeProfile> employees = employeeService.getAllEmployees();
//         List<EmployeeProfileDto> dtos = employees.stream().map(this::mapToDto).collect(Collectors.toList());
//         return ResponseEntity.ok(dtos);
//     }

//     @PutMapping("/{id}/status")
//     public ResponseEntity<EmployeeProfileDto> updateStatus(@PathVariable Long id, @RequestParam boolean active) {
//         EmployeeProfile updated = employeeService.updateEmployeeStatus(id, active);
//         return ResponseEntity.ok(mapToDto(updated));
//     }

//     @GetMapping("/lookup/{employeeId}")
//     public ResponseEntity<EmployeeProfileDto> lookupByEmployeeId(@PathVariable String employeeId) {
//         Optional<EmployeeProfile> employee = employeeService.findByEmployeeId(employeeId);
//         return employee.map(e -> ResponseEntity.ok(mapToDto(e))).orElse(ResponseEntity.notFound().build());
//     }

//     private EmployeeProfile mapToEntity(EmployeeProfileDto dto) {
//         EmployeeProfile entity = new EmployeeProfile();
//         entity.setId(dto.getId());
//         entity.setEmployeeId(dto.getEmployeeId());
//         entity.setFullName(dto.getFullName());
//         entity.setEmail(dto.getEmail());
//         entity.setTeamName(dto.getTeamName());
//         entity.setRole(dto.getRole());
//         entity.setActive(dto.getActive());
//         entity.setCreatedAt(dto.getCreatedAt());
//         return entity;
//     }

//     private EmployeeProfileDto mapToDto(EmployeeProfile entity) {
//         EmployeeProfileDto dto = new EmployeeProfileDto();
//         dto.setId(entity.getId());
//         dto.setEmployeeId(entity.getEmployeeId());
//         dto.setFullName(entity.getFullName());
//         dto.setEmail(entity.getEmail());
//         dto.setTeamName(entity.getTeamName());
//         dto.setRole(entity.getRole());
//         dto.setActive(entity.getActive());
//         dto.setCreatedAt(entity.getCreatedAt());
//         return dto;
//     }
// }