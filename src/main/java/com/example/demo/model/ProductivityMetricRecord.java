// package com.example.demo.model;

// import javax.persistence.*;
// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.util.List;

// @Entity
// @Table(name = "productivity_metric_records", 
//        uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "date"}))
// public class ProductivityMetricRecord {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name = "employee_id", nullable = false)
//     private Long employeeId;

//     @Column(nullable = false)
//     private LocalDate date;

//     @Column(name = "hours_logged")
//     private Double hoursLogged;

//     @Column(name = "tasks_completed")
//     private Integer tasksCompleted;

//     @Column(name = "meetings_attended")
//     private Integer meetingsAttended;

//     @Column(name = "productivity_score")
//     private Double productivityScore;

//     @Column(name = "raw_data_json", columnDefinition = "TEXT")
//     private String rawDataJson;

//     @Column(name = "submitted_at")
//     private LocalDateTime submittedAt;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "employee_id", insertable = false, updatable = false)
//     private EmployeeProfile employee;

//     @OneToMany(mappedBy = "metricId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     private List<AnomalyFlagRecord> anomalyFlags;

//     public ProductivityMetricRecord() {}

//     public ProductivityMetricRecord(Long employeeId, LocalDate date, Double hoursLogged, 
//                                   Integer tasksCompleted, Integer meetingsAttended) {
//         this.employeeId = employeeId;
//         this.date = date;
//         this.hoursLogged = hoursLogged;
//         this.tasksCompleted = tasksCompleted;
//         this.meetingsAttended = meetingsAttended;
//         this.submittedAt = LocalDateTime.now();
//     }

//     @PrePersist
//     protected void onCreate() {
//         if (submittedAt == null) {
//             submittedAt = LocalDateTime.now();
//         }
//     }

//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public Long getEmployeeId() { return employeeId; }
//     public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

//     public LocalDate getDate() { return date; }
//     public void setDate(LocalDate date) { this.date = date; }

//     public Double getHoursLogged() { return hoursLogged; }
//     public void setHoursLogged(Double hoursLogged) { this.hoursLogged = hoursLogged; }

//     public Integer getTasksCompleted() { return tasksCompleted; }
//     public void setTasksCompleted(Integer tasksCompleted) { this.tasksCompleted = tasksCompleted; }

//     public Integer getMeetingsAttended() { return meetingsAttended; }
//     public void setMeetingsAttended(Integer meetingsAttended) { this.meetingsAttended = meetingsAttended; }

//     public Double getProductivityScore() { return productivityScore; }
//     public void setProductivityScore(Double productivityScore) { this.productivityScore = productivityScore; }

//     public String getRawDataJson() { return rawDataJson; }
//     public void setRawDataJson(String rawDataJson) { this.rawDataJson = rawDataJson; }

//     public LocalDateTime getSubmittedAt() { return submittedAt; }
//     public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }

//     public EmployeeProfile getEmployee() { return employee; }
//     public void setEmployee(EmployeeProfile employee) { this.employee = employee; }

//     public List<AnomalyFlagRecord> getAnomalyFlags() { return anomalyFlags; }
//     public void setAnomalyFlags(List<AnomalyFlagRecord> anomalyFlags) { this.anomalyFlags = anomalyFlags; }
// }
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "productivity_metric_records")
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
    public void prePersist() {
        submittedAt = LocalDateTime.now();
    }

    // Constructors
    public ProductivityMetricRecord() {}

    public ProductivityMetricRecord(Long employeeId, LocalDate date, Double hoursLogged, Integer tasksCompleted, Integer meetingsAttended, Double productivityScore, String rawDataJson) {
        this.employeeId = employeeId;
        this.date = date;
        this.hoursLogged = hoursLogged;
        this.tasksCompleted = tasksCompleted;
        this.meetingsAttended = meetingsAttended;
        this.productivityScore = productivityScore;
        this.rawDataJson = rawDataJson;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

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
}