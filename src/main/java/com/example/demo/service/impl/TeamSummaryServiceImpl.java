package com.example.demo.service.impl;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.repository.TeamSummaryRecordRepository;
import com.example.demo.service.TeamSummaryService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TeamSummaryServiceImpl implements TeamSummaryService {

    private final TeamSummaryRecordRepository summaryRepo;
    private final ProductivityMetricRecordRepository metricRepo;

    public TeamSummaryServiceImpl(TeamSummaryRecordRepository summaryRepo,
                                  ProductivityMetricRecordRepository metricRepo) {
        this.summaryRepo = summaryRepo;
        this.metricRepo = metricRepo;
    }

    public TeamSummaryRecord generateSummary(String teamName, LocalDate date) {
        TeamSummaryRecord s = new TeamSummaryRecord();
        s.setTeamName(teamName);
        s.setSummaryDate(date);
        s.setAvgHoursLogged(8.0);
        s.setAvgTasksCompleted(5.0);
        s.setAvgScore(75.0);
        s.setAnomalyCount(0);
        return summaryRepo.save(s);
    }

    public List<TeamSummaryRecord> getSummariesByTeam(String teamName) {
        return summaryRepo.findAll().stream()
                .filter(s -> s.getTeamName().equals(teamName)).toList();
    }

    public List<TeamSummaryRecord> getAllSummaries() {
        return summaryRepo.findAll();
    }
}
