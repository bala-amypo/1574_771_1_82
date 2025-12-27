package com.example.demo.controller;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityMetricService;
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
    public ProductivityMetricRecord record(@RequestBody ProductivityMetricRecord m) {
        return service.recordMetric(m);
    }

    @GetMapping("/{id}")
    public ProductivityMetricRecord get(@PathVariable Long id) {
        return service.getMetricById(id)
                .orElseThrow(() -> new RuntimeException("Metric not found"));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ProductivityMetricRecord> getByEmployee(@PathVariable Long employeeId) {
        return service.getMetricsByEmployee(employeeId);
    }

    @GetMapping
    public List<ProductivityMetricRecord> getAll() {
        return service.getAllMetrics();
    }
}
