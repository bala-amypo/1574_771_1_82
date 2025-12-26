package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "anomaly_flags")
public class AnomalyFlagRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    private String severity;

    @Column(columnDefinition = "TEXT")
    private String details;

    private Boolean resolved = false;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
}
