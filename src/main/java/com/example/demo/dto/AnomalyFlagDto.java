// package com.example.demo.dto;

// import java.time.LocalDateTime;

// public class AnomalyFlagDto {
//     private Long id;
//     private Long employeeId;
//     private Long metricId;
//     private String ruleCode;
//     private String severity;
//     private String details;
//     private LocalDateTime flaggedAt;
//     private Boolean resolved;

//     public AnomalyFlagDto() {}

//     public AnomalyFlagDto(Long id, Long employeeId, Long metricId, String ruleCode, String severity,
//                          String details, LocalDateTime flaggedAt, Boolean resolved) {
//         this.id = id;
//         this.employeeId = employeeId;
//         this.metricId = metricId;
//         this.ruleCode = ruleCode;
//         this.severity = severity;
//         this.details = details;
//         this.flaggedAt = flaggedAt;
//         this.resolved = resolved;
//     }

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public Long getEmployeeId() { return employeeId; }
//     public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

//     public Long getMetricId() { return metricId; }
//     public void setMetricId(Long metricId) { this.metricId = metricId; }

//     public String getRuleCode() { return ruleCode; }
//     public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

//     public String getSeverity() { return severity; }
//     public void setSeverity(String severity) { this.severity = severity; }

//     public String getDetails() { return details; }
//     public void setDetails(String details) { this.details = details; }

//     public LocalDateTime getFlaggedAt() { return flaggedAt; }
//     public void setFlaggedAt(LocalDateTime flaggedAt) { this.flaggedAt = flaggedAt; }

//     public Boolean getResolved() { return resolved; }
//     public void setResolved(Boolean resolved) { this.resolved = resolved; }
// }

package com.example.demo.dto;

import java.time.LocalDateTime;

public class AnomalyFlagDto {
    private Long id;
    private Long employeeId;
    private Long metricId;
    private String ruleCode;
    private String severity;
    private String details;
    private LocalDateTime flaggedAt;
    private Boolean resolved;

    public AnomalyFlagDto() {}

    public AnomalyFlagDto(Long id, Long employeeId, Long metricId, String ruleCode, String severity, String details, LocalDateTime flaggedAt, Boolean resolved) {
        this.id = id;
        this.employeeId = employeeId;
        this.metricId = metricId;
        this.ruleCode = ruleCode;
        this.severity = severity;
        this.details = details;
        this.flaggedAt = flaggedAt;
        this.resolved = resolved;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public Long getMetricId() { return metricId; }
    public void setMetricId(Long metricId) { this.metricId = metricId; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public LocalDateTime getFlaggedAt() { return flaggedAt; }
    public void setFlaggedAt(LocalDateTime flaggedAt) { this.flaggedAt = flaggedAt; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
}