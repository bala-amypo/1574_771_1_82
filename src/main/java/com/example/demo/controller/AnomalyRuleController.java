package com.example.demo.controller;

import com.example.demo.model.AnomalyRule;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomaly-rules")
@CrossOrigin(origins = "*")

public class AnomalyRuleController {

    private final AnomalyRuleService ruleService;

    public AnomalyRuleController(AnomalyRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<AnomalyRule> createRule(@RequestBody AnomalyRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @GetMapping("/active")
    public ResponseEntity<List<AnomalyRule>> getActiveRules() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }

    @GetMapping
    public ResponseEntity<List<AnomalyRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }
}
