package com.example.demo.controller;

import com.example.demo.model.AnomalyRule;
import com.example.demo.service.AnomalyRuleService;
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
    public AnomalyRule create(@RequestBody AnomalyRule rule) {
        return anomalyRuleService.createRule(rule);
    }

    @PutMapping("/{id}")
    public AnomalyRule update(@PathVariable Long id,
                              @RequestBody AnomalyRule rule) {
        return anomalyRuleService.updateRule(id, rule);
    }

    @GetMapping("/active")
    public List<AnomalyRule> activeRules() {
        return anomalyRuleService.getActiveRules();
    }

    @GetMapping
    public List<AnomalyRule> getAll() {
        return anomalyRuleService.getAllRules();
    }
}
