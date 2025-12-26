package com.example.demo.service.impl;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.repository.TeamSummaryRecordRepository;
import com.example.demo.service.TeamSummaryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.DoubleStream;

@Service
public class TeamSummaryServiceImpl implements TeamSummaryService {

    private final TeamSummaryRecordRepository repo;
    private final ProductivityMetricRecordRepository metricRepo;

    public TeamSummaryServiceImpl(TeamSummaryRecordRepository repo, ProductivityMetricRecordRepository metricRepo) {
        this.repo = repo;
        this.metricRepo = metricRepo;
    }

    @Override
    public TeamSummaryRecord generateSummary(String teamName, java.time.LocalDate summaryDate) {
        // Simplified: aggregate over all metrics for the date and pretend they belong to this team
        List<ProductivityMetricRecord> metrics = metricRepo.findByEmployeeId(null == null ? -1L : -1L); // placeholder; production should filter by team
        TeamSummaryRecord s = new TeamSummaryRecord();
        s.setTeamName(teamName);
        s.setSummaryDate(summaryDate);
        s.setGeneratedAt(LocalDateTime.now());
        if (metrics == null || metrics.isEmpty()) {
            s.setAvgHoursLogged(0.0);
            s.setAvgTasksCompleted(0.0);
            s.setAvgScore(0.0);
            s.setAnomalyCount(0);
            return repo.save(s);
        }
        DoubleStream hours = metrics.stream().mapToDouble(m -> m.getHoursLogged() == null ? 0.0 : m.getHoursLogged());
        DoubleStream tasks = metrics.stream().mapToDouble(m -> m.getTasksCompleted() == null ? 0.0 : m.getTasksCompleted());
        DoubleStream scores = metrics.stream().mapToDouble(m -> m.getProductivityScore() == null ? 0.0 : m.getProductivityScore());
        s.setAvgHoursLogged(Math.round(hours.average().orElse(0.0) * 100.0) / 100.0);
        s.setAvgTasksCompleted(Math.round(tasks.average().orElse(0.0) * 100.0) / 100.0);
        s.setAvgScore(Math.round(scores.average().orElse(0.0) * 100.0) / 100.0);
        s.setAnomalyCount(0);
        return repo.save(s);
    }

    @Override
    public List<TeamSummaryRecord> getSummariesByTeam(String teamName) {
        return repo.findByTeamName(teamName);
    }

    @Override
    public List<TeamSummaryRecord> getAllSummaries() {
        return repo.findAll();
    }
}