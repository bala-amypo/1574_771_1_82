package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "team_summaries")
public class TeamSummaryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;
    private LocalDate summaryDate;

    private Double avgHoursLogged;
    private Double avgScore;
    private Integer anomalyCount;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public LocalDate getSummaryDate() { return summaryDate; }
    public void setSummaryDate(LocalDate summaryDate) { this.summaryDate = summaryDate; }

    public Double getAvgHoursLogged() { return avgHoursLogged; }
    public void setAvgHoursLogged(Double avgHoursLogged) { this.avgHoursLogged = avgHoursLogged; }

    public Double getAvgScore() { return avgScore; }
    public void setAvgScore(Double avgScore) { this.avgScore = avgScore; }

    public Integer getAnomalyCount() { return anomalyCount; }
    public void setAnomalyCount(Integer anomalyCount) { this.anomalyCount = anomalyCount; }
}
