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
    private Boolean resolved;
    private LocalDateTime flaggedAt;

    @PrePersist
    void onFlag() {
        flaggedAt = LocalDateTime.now();
        resolved = false;
    }

    public AnomalyFlagRecord() {}
}
