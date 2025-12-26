package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "productivity_metric_records",
       uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "date"}))
public class ProductivityMetricRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private Double hoursLogged;
    private Integer tasksCompleted;
    private Integer meetingsAttended;
    private Double productivityScore;

    @Column(columnDefinition = "TEXT")
    private String rawDataJson;

    private LocalDateTime submittedAt;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeProfile employee;

    @OneToMany(mappedBy = "metric", cascade = CascadeType.ALL)
    private List<AnomalyFlagRecord> anomalyFlags;

    @PrePersist
    public void onSubmit() {
        this.submittedAt = LocalDateTime.now();
    }

    public ProductivityMetricRecord() {}

    public Long getId() { return id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Double getHoursLogged() { return hoursLogged; }
    public void setHoursLogged(Double hoursLogged) { this.hoursLogged = hoursLogged; }

    public Integer getTasksCompleted() { return tasksCompleted; }
    public void setTasksCompleted(Integer tasksCompleted) { this.tasksCompleted = tasksCompleted; }

    public Integer getMeetingsAttended() { return meetingsAttended; }
    public void setMeetingsAttended(Integer meetingsAttended) { this.meetingsAttended = meetingsAttended; }

    public Double getProductivityScore() { return productivityScore; }
    public void setProductivityScore(Double productivityScore) { this.productivityScore = productivityScore; }

    public String getRawDataJson() { return rawDataJson; }
    public void setRawDataJson(String rawDataJson) { this.rawDataJson = rawDataJson; }

    public LocalDateTime getSubmittedAt() { return submittedAt; }

    public Long getEmployeeId() {
        return employee != null ? employee.getId() : null;
    }

    public void setEmployee(EmployeeProfile employee) {
        this.employee = employee;
    }
}
