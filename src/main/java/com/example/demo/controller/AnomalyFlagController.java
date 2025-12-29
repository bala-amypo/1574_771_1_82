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

    private final AnomalyFlagService anomalyService;

    public AnomalyFlagController(AnomalyFlagService anomalyService) {
        this.anomalyService = anomalyService;
    }

    @PostMapping
    public ResponseEntity<AnomalyFlagRecord> create(
            @RequestBody AnomalyFlagRecord record) {

        return new ResponseEntity<>(
                anomalyService.flagAnomaly(record),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<AnomalyFlagRecord> resolve(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                anomalyService.resolveFlag(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<AnomalyFlagRecord>> getAll() {
        return ResponseEntity.ok(
                anomalyService.getAllFlags()
        );
    }
}
