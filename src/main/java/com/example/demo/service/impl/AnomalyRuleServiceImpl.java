package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnomalyRule;
import com.example.demo.repository.AnomalyRuleRepository;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AnomalyRuleServiceImpl implements AnomalyRuleService {

    private final AnomalyRuleRepository repo;

    public AnomalyRuleServiceImpl(AnomalyRuleRepository repo) {
        this.repo = repo;
    }

    public AnomalyRule createRule(AnomalyRule rule) {
        return repo.save(rule);
    }

    public AnomalyRule updateRule(Long id, AnomalyRule updatedRule) {
        AnomalyRule r = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));

        r.setThresholdValue(updatedRule.getThresholdValue());
        r.setDescription(updatedRule.getDescription());
        r.setActive(updatedRule.getActive());
        return repo.save(r);
    }

    public List<AnomalyRule> getActiveRules() {
        return repo.findByActiveTrue();
    }

    public Optional<AnomalyRule> getRuleByCode(String ruleCode) {
        return repo.findAll().stream()
                .filter(r -> r.getRuleCode().equals(ruleCode))
                .findFirst();
    }

    public List<AnomalyRule> getAllRules() {
        return repo.findAll();
    }
}
