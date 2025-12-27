package com.example.demo.service;

import com.example.demo.model.AnomalyRule;
import java.util.*;

public interface AnomalyRuleService {
    AnomalyRule createRule(AnomalyRule rule);
    AnomalyRule updateRule(Long id, AnomalyRule updatedRule);
    List<AnomalyRule> getActiveRules();
    Optional<AnomalyRule> getRuleByCode(String ruleCode);
    List<AnomalyRule> getAllRules();
}
