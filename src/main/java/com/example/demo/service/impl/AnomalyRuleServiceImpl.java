package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnomalyRule;
import com.example.demo.repository.AnomalyRuleRepository;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnomalyRuleServiceImpl implements AnomalyRuleService {

    private final AnomalyRuleRepository repo;

    public AnomalyRuleServiceImpl(AnomalyRuleRepository repo) {
        this.repo = repo;
    }

    @Override
    public AnomalyRule createRule(AnomalyRule rule) {
        repo.findByRuleCode(rule.getRuleCode()).ifPresent(r -> { throw new IllegalStateException("Rule exists"); });
        return repo.save(rule);
    }

    @Override
    public AnomalyRule updateRule(Long id, AnomalyRule updatedRule) {
        return repo.findById(id).map(r -> {
            r.setDescription(updatedRule.getDescription());
            r.setThresholdType(updatedRule.getThresholdType());
            r.setThresholdValue(updatedRule.getThresholdValue());
            r.setActive(updatedRule.getActive());
            r.setRuleCode(updatedRule.getRuleCode());
            return repo.save(r);
        }).orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }

    @Override
    public List<AnomalyRule> getActiveRules() {
        return repo.findByActiveTrue();
    }

    @Override
    public Optional<AnomalyRule> getRuleByCode(String ruleCode) {
        return repo.findByRuleCode(ruleCode);
    }

    @Override
    public List<AnomalyRule> getAllRules() {
        return repo.findAll();
    }
}