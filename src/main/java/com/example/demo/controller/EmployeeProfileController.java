// package com.example.demo.controller;

// import com.example.demo.model.EmployeeProfile;
// import com.example.demo.service.EmployeeProfileService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/employees")
// public class EmployeeController {

//     @Autowired
//     private EmployeeProfileService employeeService;

//     @PostMapping
//     public ResponseEntity<EmployeeProfile> createEmployee(@RequestBody EmployeeProfile employee) {
//         EmployeeProfile created = employeeService.createEmployee(employee);
//         return ResponseEntity.ok(created);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<EmployeeProfile> getEmployee(@PathVariable Long id) {
//         try {
//             return ResponseEntity.ok(employeeService.getEmployeeById(id));
//         } catch (RuntimeException e) {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     @PatchMapping("/{id}/status")
//     public ResponseEntity<EmployeeProfile> updateStatus(@PathVariable Long id, @RequestParam boolean active) {
//         try {
//             return ResponseEntity.ok(employeeService.updateEmployeeStatus(id, active));
//         } catch (RuntimeException e) {
//             return ResponseEntity.notFound().build();
//         }
//     }
// }

package com.example.demo.controller;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")

public class EmployeeProfileController {

    private final EmployeeProfileService employeeService;

    public EmployeeProfileController(EmployeeProfileService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeProfile> createEmployee(
            @RequestBody EmployeeProfile employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfile> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeProfile>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<EmployeeProfile> updateStatus(
            @PathVariable Long id,
            @RequestParam Boolean active) {
        return ResponseEntity.ok(employeeService.updateEmployeeStatus(id, active));
    }
}
