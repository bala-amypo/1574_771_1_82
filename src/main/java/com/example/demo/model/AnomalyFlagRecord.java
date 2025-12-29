// package com.example.demo.model;

// import javax.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "anomaly_flag_records")
// public class AnomalyFlagRecord {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name = "employee_id", nullable = false)
//     private Long employeeId;

//     @Column(name = "metric_id", nullable = false)
//     private Long metricId;

//     @Column(name = "rule_code", nullable = false)
//     private String ruleCode;

//     private String severity;
//     private String details;

//     @Column(name = "flagged_at")
//     private LocalDateTime flaggedAt;

//     private Boolean resolved = false;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "employee_id", insertable = false, updatable = false)
//     private EmployeeProfile employee;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "metric_id", insertable = false, updatable = false)
//     private ProductivityMetricRecord metric;

//     public AnomalyFlagRecord() {}

//     public AnomalyFlagRecord(Long employeeId, Long metricId, String ruleCode, String severity, String details) {
//         this.employeeId = employeeId;
//         this.metricId = metricId;
//         this.ruleCode = ruleCode;
//         this.severity = severity;
//         this.details = details;
//         this.flaggedAt = LocalDateTime.now();
//         this.resolved = false;
//     }

//     @PrePersist
//     protected void onCreate() {
//         if (flaggedAt == null) {
//             flaggedAt = LocalDateTime.now();
//         }
//         if (resolved == null) {
//             resolved = false;
//         }
//     }

//     // Getters and Setters
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

//     public EmployeeProfile getEmployee() { return employee; }
//     public void setEmployee(EmployeeProfile employee) { this.employee = employee; }

//     public ProductivityMetricRecord getMetric() { return metric; }
//     public void setMetric(ProductivityMetricRecord metric) { this.metric = metric; }
// }
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "anomaly_flag_records")
public class AnomalyFlagRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private Long metricId;
    private String ruleCode;
    private String severity;
    private String details;
    private LocalDateTime flaggedAt;
    private Boolean resolved = false;

    @PrePersist
    public void prePersist() {
        flaggedAt = LocalDateTime.now();
    }

    // Constructors
    public AnomalyFlagRecord() {}

    public AnomalyFlagRecord(Long employeeId, Long metricId, String ruleCode, String severity, String details, Boolean resolved) {
        this.employeeId = employeeId;
        this.metricId = metricId;
        this.ruleCode = ruleCode;
        this.severity = severity;
        this.details = details;
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