package com.example.demo.model;

import jakarta.persistence.*;

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
    private Boolean resolved;

    public AnomalyFlagRecord() {}

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }
}
