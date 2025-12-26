package com.example.demo.controller;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.service.ProductivityMetricService;
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
    public ProductivityMetricRecord record(@RequestBody ProductivityMetricRecord record) {
        return metricService.recordMetric(record);
    }

    @PutMapping("/{id}")
    public ProductivityMetricRecord update(@PathVariable Long id,
                                           @RequestBody ProductivityMetricRecord record) {
        return metricService.updateMetric(id, record);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ProductivityMetricRecord> byEmployee(@PathVariable Long employeeId) {
        return metricService.getMetricsByEmployee(employeeId);
    }

    @GetMapping("/{id}")
    public ProductivityMetricRecord getById(@PathVariable Long id) {
        return metricService.getMetricById(id)
                .orElseThrow(() -> new RuntimeException("Metric not found"));
    }

    @GetMapping
    public List<ProductivityMetricRecord> getAll() {
        return metricService.getAllMetrics();
    }
}
