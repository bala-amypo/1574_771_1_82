// package com.example.demo.service.impl;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.AnomalyRule;
// import com.example.demo.repository.AnomalyRuleRepository;
// import com.example.demo.service.AnomalyRuleService;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class AnomalyRuleServiceImpl implements AnomalyRuleService {

//     private final AnomalyRuleRepository anomalyRuleRepository;

//     public AnomalyRuleServiceImpl(AnomalyRuleRepository anomalyRuleRepository) {
//         this.anomalyRuleRepository = anomalyRuleRepository;
//     }

//     @Override
//     public AnomalyRule createRule(AnomalyRule rule) {
//         return anomalyRuleRepository.save(rule);
//     }

//     @Override
//     public AnomalyRule updateRule(Long id, AnomalyRule updatedRule) {
//         AnomalyRule existing = anomalyRuleRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
        
//         if (updatedRule.getDescription() != null) {
//             existing.setDescription(updatedRule.getDescription());
//         }
//         if (updatedRule.getThresholdType() != null) {
//             existing.setThresholdType(updatedRule.getThresholdType());
//         }
//         if (updatedRule.getThresholdValue() != null) {
//             existing.setThresholdValue(updatedRule.getThresholdValue());
//         }
//         if (updatedRule.getActive() != null) {
//             existing.setActive(updatedRule.getActive());
//         }
        
//         return anomalyRuleRepository.save(existing);
//     }

//     @Override
//     public List<AnomalyRule> getActiveRules() {
//         return anomalyRuleRepository.findByActiveTrue();
//     }

//     @Override
//     public Optional<AnomalyRule> getRuleByCode(String ruleCode) {
//         return anomalyRuleRepository.findAll().stream()
//                 .filter(rule -> rule.getRuleCode().equals(ruleCode))
//                 .findFirst();
//     }

//     @Override
//     public List<AnomalyRule> getAllRules() {
//         return anomalyRuleRepository.findAll();
//     }
// }
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

    private final AnomalyRuleRepository ruleRepository;

    public AnomalyRuleServiceImpl(AnomalyRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public AnomalyRule createRule(AnomalyRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public AnomalyRule updateRule(Long id, AnomalyRule updatedRule) {
        AnomalyRule existing = ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
        existing.setDescription(updatedRule.getDescription());
        existing.setThresholdType(updatedRule.getThresholdType());
        existing.setThresholdValue(updatedRule.getThresholdValue());
        existing.setActive(updatedRule.getActive());
        return ruleRepository.save(existing);
    }

    @Override
    public List<AnomalyRule> getActiveRules() {
        return ruleRepository.findByActiveTrue();
    }

    @Override
    public Optional<AnomalyRule> getRuleByCode(String ruleCode) {
        return ruleRepository.findAll().stream()
                .filter(r -> ruleCode.equals(r.getRuleCode()))
                .findFirst();
    }

    @Override
    public List<AnomalyRule> getAllRules() {
        return ruleRepository.findAll();
    }
}