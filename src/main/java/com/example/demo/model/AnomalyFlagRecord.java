package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "anomaly_flag_records")
public class AnomalyFlagRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // employee relation (employee_id column)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private EmployeeProfile employeeProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metric_id", nullable = false)
    private ProductivityMetricRecord metric;

    private String ruleCode;
    private String severity;
    @Lob
    private String details;
    private LocalDateTime flaggedAt;
    private Boolean resolved = false;

    public AnomalyFlagRecord() {}

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public EmployeeProfile getEmployeeProfile() { return employeeProfile; }
    public void setEmployeeProfile(EmployeeProfile employeeProfile) { this.employeeProfile = employeeProfile; }
    public ProductivityMetricRecord getMetric() { return metric; }
    public void setMetric(ProductivityMetricRecord metric) { this.metric = metric; }
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