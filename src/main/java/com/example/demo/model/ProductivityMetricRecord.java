package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productivity_metric_records", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"employee_id", "date"})
})
public class ProductivityMetricRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // store employee id as FK column
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    // ManyToOne relation linked to employee_id column (read-only to avoid duplicate writes)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private EmployeeProfile employeeProfile;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    private Double hoursLogged = 0.0;
    private Integer tasksCompleted = 0;
    private Integer meetingsAttended = 0;
    private Double productivityScore = 0.0;
    @Lob
    private String rawDataJson;
    private LocalDateTime submittedAt;

    @OneToMany(mappedBy = "metric", cascade = CascadeType.ALL)
    private List<AnomalyFlagRecord> flags = new ArrayList<>();

    public ProductivityMetricRecord() {}

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // employeeId property used by repository queries
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public EmployeeProfile getEmployeeProfile() { return employeeProfile; }
    public void setEmployeeProfile(EmployeeProfile employeeProfile) {
        this.employeeProfile = employeeProfile;
        if (employeeProfile != null) {
            this.employeeId = employeeProfile.getId();
        }
    }

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
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
    public List<AnomalyFlagRecord> getFlags() { return flags; }
}