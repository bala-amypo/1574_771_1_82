package com.example.demo.service.impl;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.TeamSummaryRecordRepository;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.TeamSummaryService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class TeamSummaryServiceImpl implements TeamSummaryService {

    private final TeamSummaryRecordRepository summaryRepo;
    private final ProductivityMetricRecordRepository metricRepo;

    public TeamSummaryServiceImpl(TeamSummaryRecordRepository summaryRepo,
                                  ProductivityMetricRecordRepository metricRepo) {
        this.summaryRepo = summaryRepo;
        this.metricRepo = metricRepo;
    }

    public TeamSummaryRecord generateSummary(String teamName, LocalDate summaryDate) {

        List<ProductivityMetricRecord> metrics = metricRepo.findAll();

        double avgHours = metrics.stream()
                .mapToDouble(m -> m.getHoursLogged() == null ? 0 : m.getHoursLogged())
                .average().orElse(0);

        double avgTasks = metrics.stream()
                .mapToInt(m -> m.getTasksCompleted() == null ? 0 : m.getTasksCompleted())
                .average().orElse(0);

        double avgScore = metrics.stream()
                .mapToDouble(m -> m.getProductivityScore() == null ? 0 : m.getProductivityScore())
                .average().orElse(0);

        TeamSummaryRecord t = new TeamSummaryRecord();
        t.setTeamName(teamName);
        t.setSummaryDate(summaryDate);
        t.setAvgHoursLogged(avgHours);
        t.setAvgTasksCompleted(avgTasks);
        t.setAvgScore(avgScore);
        t.setAnomalyCount(0);

        return summaryRepo.save(t);
    }

    public List<TeamSummaryRecord> getSummariesByTeam(String teamName) {
        return summaryRepo.findByTeamName(teamName);
    }

    public List<TeamSummaryRecord> getAllSummaries() {
        return summaryRepo.findAll();
    }
}
