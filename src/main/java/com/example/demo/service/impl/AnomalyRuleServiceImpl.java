package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnomalyRule;
import com.example.demo.repository.AnomalyRuleRepository;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnomalyRuleServiceImpl implements AnomalyRuleService {

    private final AnomalyRuleRepository repo;

    public AnomalyRuleServiceImpl(AnomalyRuleRepository repo) {
        this.repo = repo;
    }

    public AnomalyRule createRule(AnomalyRule rule) {
        return repo.save(rule);
    }

    public AnomalyRule updateRule(Long id, AnomalyRule rule) {
        AnomalyRule r = getRuleById(id);
        rule.setId(r.getId());
        return repo.save(rule);
    }

    public List<AnomalyRule> getActiveRules() {
        return repo.findByActiveTrue();
    }

    public List<AnomalyRule> getAllRules() {
        return repo.findAll();
    }

    public AnomalyRule getRuleById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }
}
