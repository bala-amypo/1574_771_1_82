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

    public TeamSummaryServiceImpl(
            TeamSummaryRecordRepository summaryRepo,
            ProductivityMetricRecordRepository metricRepo) {
        this.summaryRepo = summaryRepo;
        this.metricRepo = metricRepo;
    }

    @Override
    public TeamSummaryRecord generateSummary(String teamName, LocalDate summaryDate) {
        TeamSummaryRecord record = new TeamSummaryRecord();
        record.setTeamName(teamName);
        record.setSummaryDate(summaryDate);
        record.setAvgHoursLogged(0.0);
        record.setAvgTasksCompleted(0.0);
        record.setAvgScore(0.0);
        record.setAnomalyCount(0);
        return summaryRepo.save(record);
    }

    @Override
    public List<TeamSummaryRecord> getSummariesByTeam(String teamName) {
        return summaryRepo.findByTeamName(teamName);
    }

    @Override
    public List<TeamSummaryRecord> getAllSummaries() {
        return summaryRepo.findAll();
    }
}
