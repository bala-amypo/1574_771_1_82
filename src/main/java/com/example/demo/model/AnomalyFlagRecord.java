package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "anomaly_flag_records")
public class AnomalyFlagRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private String severity;
    private String details;
    private Boolean resolved = false;
    private LocalDateTime flaggedAt;

    @ManyToOne
    private EmployeeProfile employee;

    @ManyToOne
    private ProductivityMetricRecord metric;

    @PrePersist
    public void onFlag() {
        this.flaggedAt = LocalDateTime.now();
    }

    public AnomalyFlagRecord() {}

    public Long getId() { return id; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }

    public LocalDateTime getFlaggedAt() { return flaggedAt; }
}
