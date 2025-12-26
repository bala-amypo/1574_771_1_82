package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "team_summary_records", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"teamName", "summaryDate"})
})
public class TeamSummaryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private LocalDate summaryDate;
    private Double avgHoursLogged = 0.0;
    private Double avgTasksCompleted = 0.0;
    private Double avgScore = 0.0;
    private Integer anomalyCount = 0;
    private LocalDateTime generatedAt;

    public TeamSummaryRecord() {}

    // getters/setters
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