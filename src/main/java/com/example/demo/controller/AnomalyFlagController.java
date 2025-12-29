package com.example.demo.controller;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.service.AnomalyFlagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
@CrossOrigin(origins = "*")

public class AnomalyFlagController {

    private final AnomalyFlagService flagService;

    public AnomalyFlagController(AnomalyFlagService flagService) {
        this.flagService = flagService;
    }

    @PostMapping
    public ResponseEntity<AnomalyFlagRecord> flagMetric(
            @RequestBody AnomalyFlagRecord flag) {
        return ResponseEntity.ok(flagService.flagMetric(flag));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<AnomalyFlagRecord> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(flagService.resolveFlag(id));
    }

    @GetMapping
    public ResponseEntity<List<AnomalyFlagRecord>> getAll() {
        return ResponseEntity.ok(flagService.getAllFlags());
    }
}
