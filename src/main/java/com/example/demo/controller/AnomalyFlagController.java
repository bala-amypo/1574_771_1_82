package com.example.demo.controller;

import com.example.demo.dto.AnomalyFlagDto;
import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.service.AnomalyFlagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyFlagController {

    private final AnomalyFlagService anomalyFlagService;

    public AnomalyFlagController(AnomalyFlagService anomalyFlagService) {
        this.anomalyFlagService = anomalyFlagService;
    }

    @PostMapping
    public ResponseEntity<AnomalyFlagDto> createAnomalyFlag(@RequestBody AnomalyFlagDto dto) {
        AnomalyFlagRecord flag = new AnomalyFlagRecord(dto.getEmployeeId(), dto.getMetricId(),
                dto.getRuleCode(), dto.getSeverity(), dto.getDetails());
        
        AnomalyFlagRecord created = anomalyFlagService.flagAnomaly(flag);
        return ResponseEntity.ok(toDto(created));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<AnomalyFlagDto> resolveFlag(@PathVariable Long id) {
        AnomalyFlagRecord resolved = anomalyFlagService.resolveFlag(id);
        return ResponseEntity.ok(toDto(resolved));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AnomalyFlagDto>> getFlagsByEmployee(@PathVariable Long employeeId) {
        List<AnomalyFlagRecord> flags = anomalyFlagService.getFlagsByEmployee(employeeId);
        List<AnomalyFlagDto> dtos = flags.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/metric/{metricId}")
    public ResponseEntity<List<AnomalyFlagDto>> getFlagsByMetric(@PathVariable Long metricId) {
        List<AnomalyFlagRecord> flags = anomalyFlagService.getFlagsByMetric(metricId);
        List<AnomalyFlagDto> dtos = flags.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping
    public ResponseEntity<List<AnomalyFlagDto>> getAllFlags() {
        List<AnomalyFlagRecord> flags = anomalyFlagService.getAllFlags();
        List<AnomalyFlagDto> dtos = flags.stream().map(this::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private AnomalyFlagDto toDto(AnomalyFlagRecord flag) {
        return new AnomalyFlagDto(flag.getId(), flag.getEmployeeId(), flag.getMetricId(),
                flag.getRuleCode(), flag.getSeverity(), flag.getDetails(), flag.getFlaggedAt(), flag.getResolved());
    }
}





// package com.example.demo.controller;

// import com.example.demo.dto.AnomalyFlagDto;
// import com.example.demo.model.AnomalyFlagRecord;
// import com.example.demo.service.AnomalyFlagService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/api/anomalies")
// public class AnomalyFlagController {

//     private final AnomalyFlagService flagService;

//     public AnomalyFlagController(AnomalyFlagService flagService) {
//         this.flagService = flagService;
//     }

//     @PostMapping
//     public ResponseEntity<AnomalyFlagDto> flagAnomaly(@RequestBody AnomalyFlagDto dto) {
//         AnomalyFlagRecord entity = mapToEntity(dto);
//         AnomalyFlagRecord saved = flagService.flagAnomaly(entity);
//         return ResponseEntity.ok(mapToDto(saved));
//     }

//     @PutMapping("/{id}/resolve")
//     public ResponseEntity<AnomalyFlagDto> resolveFlag(@PathVariable Long id) {
//         AnomalyFlagRecord resolved = flagService.resolveFlag(id);
//         return ResponseEntity.ok(mapToDto(resolved));
//     }

//     @GetMapping("/employee/{employeeId}")
//     public ResponseEntity<List<AnomalyFlagDto>> getFlagsByEmployee(@PathVariable Long employeeId) {
//         List<AnomalyFlagRecord> flags = flagService.getFlagsByEmployee(employeeId);
//         List<AnomalyFlagDto> dtos = flags.stream().map(this::mapToDto).collect(Collectors.toList());
//         return ResponseEntity.ok(dtos);
//     }

//     @GetMapping("/metric/{metricId}")
//     public ResponseEntity<List<AnomalyFlagDto>> getFlagsByMetric(@PathVariable Long metricId) {
//         List<AnomalyFlagRecord> flags = flagService.getFlagsByMetric(metricId);
//         List<AnomalyFlagDto> dtos = flags.stream().map(this::mapToDto).collect(Collectors.toList());
//         return ResponseEntity.ok(dtos);
//     }

//     @GetMapping
//     public ResponseEntity<List<AnomalyFlagDto>> getAllFlags() {
//         List<AnomalyFlagRecord> flags = flagService.getAllFlags();
//         List<AnomalyFlagDto> dtos = flags.stream().map(this::mapToDto).collect(Collectors.toList());
//         return ResponseEntity.ok(dtos);
//     }

//     private AnomalyFlagRecord mapToEntity(AnomalyFlagDto dto) {
//         AnomalyFlagRecord entity = new AnomalyFlagRecord();
//         entity.setId(dto.getId());
//         entity.setEmployeeId(dto.getEmployeeId());
//         entity.setMetricId(dto.getMetricId());
//         entity.setRuleCode(dto.getRuleCode());
//         entity.setSeverity(dto.getSeverity());
//         entity.setDetails(dto.getDetails());
//         entity.setFlaggedAt(dto.getFlaggedAt());
//         entity.setResolved(dto.getResolved());
//         return entity;
//     }

//     private AnomalyFlagDto mapToDto(AnomalyFlagRecord entity) {
//         AnomalyFlagDto dto = new AnomalyFlagDto();
//         dto.setId(entity.getId());
//         dto.setEmployeeId(entity.getEmployeeId());
//         dto.setMetricId(entity.getMetricId());
//         dto.setRuleCode(entity.getRuleCode());
//         dto.setSeverity(entity.getSeverity());
//         dto.setDetails(entity.getDetails());
//         dto.setFlaggedAt(entity.getFlaggedAt());
//         dto.setResolved(entity.getResolved());
//         return dto;
//     }
// }