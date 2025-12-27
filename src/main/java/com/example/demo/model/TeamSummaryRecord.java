package com.example.demo.model;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "team_summary_records")
public class TeamSummaryRecord {

    @Id
    @GeneratedValue
    private Long id;

    private String teamName;
    private LocalDate summaryDate;
    private Double avgHoursLogged;
    private Double avgTasksCompleted;
    private Double avgScore;
    private Integer anomalyCount;
    private LocalDateTime generatedAt = LocalDateTime.now();

    public TeamSummaryRecord() {}

    public void setTeamName(String teamName) { this.teamName = teamName; }
    public String getTeamName() { return teamName; }

    public void setAvgHoursLogged(Double avgHoursLogged) { this.avgHoursLogged = avgHoursLogged; }
    public Double getAvgHoursLogged() { return avgHoursLogged; }

    public void setAvgScore(Double avgScore) { this.avgScore = avgScore; }
    public Double getAvgScore() { return avgScore; }

    public void setAnomalyCount(Integer anomalyCount) { this.anomalyCount = anomalyCount; }
    public Integer getAnomalyCount() { return anomalyCount; }

    public void setSummaryDate(LocalDate summaryDate) { this.summaryDate = summaryDate; }
    public LocalDate getSummaryDate() { return summaryDate; }
}
