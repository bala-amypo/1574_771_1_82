package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "productivity_metric_records",
    uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id","date"})
)
public class ProductivityMetricRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private LocalDate date;
    private Double hoursLogged;
    private Integer tasksCompleted;
    private Integer meetingsAttended;
    private Double productivityScore;
    private String rawDataJson;
    private LocalDateTime submittedAt;

    @PrePersist
    void onSubmit() {
        submittedAt = LocalDateTime.now();
    }

    public ProductivityMetricRecord() {}
}
