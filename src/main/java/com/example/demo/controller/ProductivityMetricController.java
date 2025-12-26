package com.example.demo.controller;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityMetricService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityMetricService metricService;

    public ProductivityMetricController(ProductivityMetricService metricService) {
        this.metricService = metricService;
    }

    @PostMapping
    public ResponseEntity<ProductivityMetricRecord> record(
            @RequestBody Map<String, Object> payload) {

        ProductivityMetricRecord record = new ProductivityMetricRecord();

        EmployeeProfile employee = new EmployeeProfile();
        employee.setId(Long.valueOf(payload.get("employeeId").toString()));
        record.setEmployee(employee);

        record.setDate(java.time.LocalDate.parse(payload.get("date").toString()));
        record.setHoursLogged(Double.valueOf(payload.get("hoursLogged").toString()));
        record.setTasksCompleted(Integer.valueOf(payload.get("tasksCompleted").toString()));
        record.setMeetingsAttended(Integer.valueOf(payload.get("meetingsAttended").toString()));
        record.setRawDataJson(payload.get("rawDataJson").toString());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(metricService.recordMetric(record));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductivityMetricRecord> update(
            @PathVariable Long id,
            @RequestBody ProductivityMetricRecord record) {

        return ResponseEntity.ok(metricService.updateMetric(id, record));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ProductivityMetricRecord>> byEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                metricService.getMetricsByEmployee(employeeId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductivityMetricRecord> getById(
            @PathVariable Long id) {

        return metricService.getMetricById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductivityMetricRecord>> getAll() {
        return ResponseEntity.ok(metricService.getAllMetrics());
    }
}
