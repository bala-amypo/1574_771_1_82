package com.example.demo.controller;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.service.AnomalyFlagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyFlagController {

    private final AnomalyFlagService anomalyFlagService;

    public AnomalyFlagController(AnomalyFlagService anomalyFlagService) {
        this.anomalyFlagService = anomalyFlagService;
    }

    @PostMapping
    public ResponseEntity<AnomalyFlagRecord> create(@RequestBody AnomalyFlagRecord record) {
        AnomalyFlagRecord saved = anomalyFlagService.flagAnomaly(record);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<AnomalyFlagRecord> resolve(@PathVariable Long id) {
        AnomalyFlagRecord resolved = anomalyFlagService.resolveFlag(id);
        return ResponseEntity.ok(resolved);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AnomalyFlagRecord>> byEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(anomalyFlagService.getFlagsByEmployee(employeeId));
    }

    @GetMapping("/metric/{metricId}")
    public ResponseEntity<List<AnomalyFlagRecord>> byMetric(@PathVariable Long metricId) {
        return ResponseEntity.ok(anomalyFlagService.getFlagsByMetric(metricId));
    }

    @GetMapping
    public ResponseEntity<List<AnomalyFlagRecord>> getAll() {
        return ResponseEntity.ok(anomalyFlagService.getAllFlags());
    }
}
