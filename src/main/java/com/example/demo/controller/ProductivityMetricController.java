package com.example.demo.controller;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityMetricService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class ProductivityMetricController {

    private final ProductivityMetricService service;

    public ProductivityMetricController(
            ProductivityMetricService service) {
        this.service = service;
    }

    @PostMapping
    public ProductivityMetricRecord record(
            @RequestBody ProductivityMetricRecord record) {
        return service.recordMetric(record);
    }

    @PutMapping("/{id}")
    public ProductivityMetricRecord update(
            @PathVariable Long id,
            @RequestBody ProductivityMetricRecord record) {
        return service.updateMetric(id, record);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ProductivityMetricRecord> byEmployee(
            @PathVariable Long employeeId) {
        return service.getMetricsByEmployee(employeeId);
    }

    @GetMapping
    public List<ProductivityMetricRecord> getAll() {
        return service.getAllMetrics();
    }
}
