package com.example.demo.controller;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityMetricService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityMetricService service;

    public ProductivityMetricController(ProductivityMetricService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductivityMetricRecord> record(
            @RequestBody ProductivityMetricRecord record) {
        return new ResponseEntity<>(service.recordMetric(record), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductivityMetricRecord> update(
            @PathVariable Long id,
            @RequestBody ProductivityMetricRecord record) {
        return ResponseEntity.ok(service.updateMetric(id, record));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductivityMetricRecord> getById(@PathVariable Long id) {
        return service.getMetricById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ProductivityMetricRecord>> byEmployee(
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(service.getMetricsByEmployee(employeeId));
    }

    @GetMapping
    public ResponseEntity<List<ProductivityMetricRecord>> getAll() {
        return ResponseEntity.ok(service.getAllMetrics());
    }
}
