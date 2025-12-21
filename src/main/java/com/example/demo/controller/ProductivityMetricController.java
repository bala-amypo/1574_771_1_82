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

    @PutMapping("/{id}")
    public ProductivityMetricRecord update(@PathVariable Long id, @RequestBody ProductivityMetricRecord m) {
        return service.updateMetric(id, m);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ProductivityMetricRecord> byEmployee(@PathVariable Long employeeId) {
        return service.getMetricsByEmployee(employeeId);
    }

    @GetMapping("/{id}")
    public ProductivityMetricRecord get(@PathVariable Long id) {
        return service.getMetricById(id).orElseThrow();
    }

    @GetMapping
    public List<ProductivityMetricRecord> getAll() {
        return service.getAllMetrics();
    }
}
