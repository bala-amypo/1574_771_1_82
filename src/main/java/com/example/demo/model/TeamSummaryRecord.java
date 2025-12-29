// package com.example.demo.model;

// import javax.persistence.*;
// import java.time.LocalDate;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "team_summary_records")
// public class TeamSummaryRecord {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name = "team_name", nullable = false)
//     private String teamName;

//     @Column(name = "summary_date", nullable = false)
//     private LocalDate summaryDate;

//     @Column(name = "avg_hours_logged")
//     private Double avgHoursLogged;

//     @Column(name = "avg_tasks_completed")
//     private Double avgTasksCompleted;

//     @Column(name = "avg_score")
//     private Double avgScore;

//     @Column(name = "anomaly_count")
//     private Integer anomalyCount;

//     @Column(name = "generated_at")
//     private LocalDateTime generatedAt;

//     public TeamSummaryRecord() {}

//     public TeamSummaryRecord(String teamName, LocalDate summaryDate, Double avgHoursLogged, 
//                            Double avgTasksCompleted, Double avgScore, Integer anomalyCount) {
//         this.teamName = teamName;
//         this.summaryDate = summaryDate;
//         this.avgHoursLogged = avgHoursLogged;
//         this.avgTasksCompleted = avgTasksCompleted;
//         this.avgScore = avgScore;
//         this.anomalyCount = anomalyCount;
//         this.generatedAt = LocalDateTime.now();
//     }

//     @PrePersist
//     protected void onCreate() {
//         if (generatedAt == null) {
//             generatedAt = LocalDateTime.now();
//         }
//     }

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getTeamName() { return teamName; }
//     public void setTeamName(String teamName) { this.teamName = teamName; }

//     public LocalDate getSummaryDate() { return summaryDate; }
//     public void setSummaryDate(LocalDate summaryDate) { this.summaryDate = summaryDate; }

//     public Double getAvgHoursLogged() { return avgHoursLogged; }
//     public void setAvgHoursLogged(Double avgHoursLogged) { this.avgHoursLogged = avgHoursLogged; }

//     public Double getAvgTasksCompleted() { return avgTasksCompleted; }
//     public void setAvgTasksCompleted(Double avgTasksCompleted) { this.avgTasksCompleted = avgTasksCompleted; }

//     public Double getAvgScore() { return avgScore; }
//     public void setAvgScore(Double avgScore) { this.avgScore = avgScore; }

//     public Integer getAnomalyCount() { return anomalyCount; }
//     public void setAnomalyCount(Integer anomalyCount) { this.anomalyCount = anomalyCount; }

//     public LocalDateTime getGeneratedAt() { return generatedAt; }
//     public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
// }
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "team_summary_records")
public class TeamSummaryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private LocalDate summaryDate;
    private Double avgHoursLogged;
    private Double avgTasksCompleted;
    private Double avgScore;
    private Integer anomalyCount;
    private LocalDateTime generatedAt;

    @PrePersist
    public void prePersist() {
        generatedAt = LocalDateTime.now();
    }

    // Constructors
    public TeamSummaryRecord() {}

    public TeamSummaryRecord(String teamName, LocalDate summaryDate, Double avgHoursLogged, Double avgTasksCompleted, Double avgScore, Integer anomalyCount) {
        this.teamName = teamName;
        this.summaryDate = summaryDate;
        this.avgHoursLogged = avgHoursLogged;
        this.avgTasksCompleted = avgTasksCompleted;
        this.avgScore = avgScore;
        this.anomalyCount = anomalyCount;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public LocalDate getSummaryDate() { return summaryDate; }
    public void setSummaryDate(LocalDate summaryDate) { this.summaryDate = summaryDate; }

    public Double getAvgHoursLogged() { return avgHoursLogged; }
    public void setAvgHoursLogged(Double avgHoursLogged) { this.avgHoursLogged = avgHoursLogged; }

    public Double getAvgTasksCompleted() { return avgTasksCompleted; }
    public void setAvgTasksCompleted(Double avgTasksCompleted) { this.avgTasksCompleted = avgTasksCompleted; }

    public Double getAvgScore() { return avgScore; }
    public void setAvgScore(Double avgScore) { this.avgScore = avgScore; }

    public Integer getAnomalyCount() { return anomalyCount; }
    public void setAnomalyCount(Integer anomalyCount) { this.anomalyCount = anomalyCount; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}