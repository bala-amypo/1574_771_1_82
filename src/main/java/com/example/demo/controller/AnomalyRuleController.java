package com.example.demo.controller;

import com.example.demo.model.AnomalyRule;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomaly-rules")
public class AnomalyRuleController {

    private final AnomalyRuleService anomalyRuleService;

    public AnomalyRuleController(AnomalyRuleService anomalyRuleService) {
        this.anomalyRuleService = anomalyRuleService;
    }

    @PostMapping
    public ResponseEntity<AnomalyRule> create(@RequestBody AnomalyRule rule) {
        return new ResponseEntity<>(
                anomalyRuleService.createRule(rule),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnomalyRule> update(
            @PathVariable Long id,
            @RequestBody AnomalyRule rule) {

        return ResponseEntity.ok(
                anomalyRuleService.updateRule(id, rule)
        );
    }

    @GetMapping("/active")
    public ResponseEntity<List<AnomalyRule>> activeRules() {
        return ResponseEntity.ok(
                anomalyRuleService.getActiveRules()
        );
    }

    @GetMapping
    public ResponseEntity<List<AnomalyRule>> getAll() {
        return ResponseEntity.ok(
                anomalyRuleService.getAllRules()
        );
    }
}
