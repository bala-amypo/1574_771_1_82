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

    private final ProductivityMetricService metricService;

    public ProductivityMetricController(ProductivityMetricService metricService) {
        this.metricService = metricService;
    }

    @PostMapping
    public ResponseEntity<ProductivityMetricRecord> record(
            @RequestBody ProductivityMetricRecord record) {

        return new ResponseEntity<>(
                metricService.recordMetric(record),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductivityMetricRecord> update(
            @PathVariable Long id,
            @RequestBody ProductivityMetricRecord record) {

        return ResponseEntity.ok(
                metricService.updateMetric(id, record)
        );
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
        return ResponseEntity.ok(
                metricService.getAllMetrics()
        );
    }
}
