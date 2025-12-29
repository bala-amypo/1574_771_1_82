package com.example.demo.controller;

import com.example.demo.dto.AnomalyRuleDto;
import com.example.demo.model.AnomalyRule;
import com.example.demo.service.AnomalyRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/anomaly-rules")
public class AnomalyRuleController {

    private final AnomalyRuleService anomalyRuleService;

    public AnomalyRuleController(AnomalyRuleService anomalyRuleService) {
        this.anomalyRuleService = anomalyRuleService;
    }

    @PostMapping
    public ResponseEntity<AnomalyRuleDto> createRule(@RequestBody AnomalyRuleDto dto) {
        AnomalyRule rule = new AnomalyRule(dto.getRuleCode(), dto.getDescription(), 
                dto.getThresholdType(), dto.getThresholdValue());
        rule.setActive(dto.getActive() != null ? dto.getActive() : true);
        
        AnomalyRule created = anomalyRuleService.createRule(rule);
        return ResponseEntity.ok(toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnomalyRuleDto> updateRule(@PathVariable Long id, @RequestBody AnomalyRuleDto dto) {
        AnomalyRule updated = new AnomalyRule();
        updated.setDescription(dto.getDescription());
        updated.setThresholdType(dto.getThresholdType());
        updated.setThresholdValue(dto.getThresholdValue());
        updated.setActive(dto.getActive());
        
        AnomalyRule result = anomalyRuleService.updateRule(id, updated);
        return ResponseEntity.ok(toDto(result));
    }

    @GetMapping("/active")
    public ResponseEntity<List<AnomalyRuleDto>> getActiveRules() {
        List<AnomalyRule> rules = anomalyRuleService.getActiveRules();
        List<AnomalyRuleDto> dtos = rules.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnomalyRuleDto> getRule(@PathVariable Long id) {
        List<AnomalyRule> allRules = anomalyRuleService.getAllRules();
        return allRules.stream()
                .filter(rule -> rule.getId().equals(id))
                .findFirst()
                .map(rule -> ResponseEntity.ok(toDto(rule)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AnomalyRuleDto>> getAllRules() {
        List<AnomalyRule> rules = anomalyRuleService.getAllRules();
        List<AnomalyRuleDto> dtos = rules.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private AnomalyRuleDto toDto(AnomalyRule rule) {
        return new AnomalyRuleDto(rule.getId(), rule.getRuleCode(), rule.getDescription(),
                rule.getThresholdType(), rule.getThresholdValue(), rule.getActive());
    }
}




// package com.example.demo.controller;

// import com.example.demo.dto.AnomalyRuleDto;
// import com.example.demo.model.AnomalyRule;
// import com.example.demo.service.AnomalyRuleService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/api/anomaly-rules")
// public class AnomalyRuleController {

//     private final AnomalyRuleService ruleService;

//     public AnomalyRuleController(AnomalyRuleService ruleService) {
//         this.ruleService = ruleService;
//     }

//     @PostMapping
//     public ResponseEntity<AnomalyRuleDto> createRule(@RequestBody AnomalyRuleDto dto) {
//         AnomalyRule entity = mapToEntity(dto);
//         AnomalyRule saved = ruleService.createRule(entity);
//         return ResponseEntity.ok(mapToDto(saved));
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<AnomalyRuleDto> updateRule(@PathVariable Long id, @RequestBody AnomalyRuleDto dto) {
//         AnomalyRule entity = mapToEntity(dto);
//         AnomalyRule updated = ruleService.updateRule(id, entity);
//         return ResponseEntity.ok(mapToDto(updated));
//     }

//     @GetMapping("/active")
//     public ResponseEntity<List<AnomalyRuleDto>> getActiveRules() {
//         List<AnomalyRule> rules = ruleService.getActiveRules();
//         List<AnomalyRuleDto> dtos = rules.stream().map(this::mapToDto).collect(Collectors.toList());
//         return ResponseEntity.ok(dtos);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<AnomalyRuleDto> getRule(@PathVariable Long id) {
//         Optional<AnomalyRule> rule = ruleService.getAllRules().stream().filter(r -> r.getId().equals(id)).findFirst();
//         return rule.map(r -> ResponseEntity.ok(mapToDto(r))).orElse(ResponseEntity.notFound().build());
//     }

//     @GetMapping
//     public ResponseEntity<List<AnomalyRuleDto>> getAllRules() {
//         List<AnomalyRule> rules = ruleService.getAllRules();
//         List<AnomalyRuleDto> dtos = rules.stream().map(this::mapToDto).collect(Collectors.toList());
//         return ResponseEntity.ok(dtos);
//     }

//     private AnomalyRule mapToEntity(AnomalyRuleDto dto) {
//         AnomalyRule entity = new AnomalyRule();
//         entity.setId(dto.getId());
//         entity.setRuleCode(dto.getRuleCode());
//         entity.setDescription(dto.getDescription());
//         entity.setThresholdType(dto.getThresholdType());
//         entity.setThresholdValue(dto.getThresholdValue());
//         entity.setActive(dto.getActive());
//         return entity;
//     }

//     private AnomalyRuleDto mapToDto(AnomalyRule entity) {
//         AnomalyRuleDto dto = new AnomalyRuleDto();
//         dto.setId(entity.getId());
//         dto.setRuleCode(entity.getRuleCode());
//         dto.setDescription(entity.getDescription());
//         dto.setThresholdType(entity.getThresholdType());
//         dto.setThresholdValue(entity.getThresholdValue());
//         dto.setActive(entity.getActive());
//         return dto;
//     }
// }