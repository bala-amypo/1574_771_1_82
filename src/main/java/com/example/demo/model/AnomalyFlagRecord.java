package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "anomaly_flag_records")
public class AnomalyFlagRecord {

    @Id
    @GeneratedValue
    private Long id;

    private Long employeeId;
    private Long metricId;
    private String ruleCode;
    private String severity;
    private String details;
    private Boolean resolved = false;
    private LocalDateTime flaggedAt = LocalDateTime.now();

    public AnomalyFlagRecord() {}

    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }
    public String getRuleCode() { return ruleCode; }

    public void setResolved(Boolean resolved) { this.resolved = resolved; }
    public Boolean getResolved() { return resolved; }

    public void setDetails(String details) { this.details = details; }
    public String getDetails() { return details; }

    public void setSeverity(String severity) { this.severity = severity; }
    public String getSeverity() { return severity; }

    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public void setMetricId(Long metricId) { this.metricId = metricId; }
}
