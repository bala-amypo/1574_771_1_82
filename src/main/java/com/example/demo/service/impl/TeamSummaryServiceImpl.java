// package com.example.demo.service.impl;

// import com.example.demo.model.ProductivityMetricRecord;
// import com.example.demo.model.TeamSummaryRecord;
// import com.example.demo.repository.ProductivityMetricRecordRepository;
// import com.example.demo.repository.TeamSummaryRecordRepository;
// import com.example.demo.service.TeamSummaryService;
// import org.springframework.stereotype.Service;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// public class TeamSummaryServiceImpl implements TeamSummaryService {

//     private final TeamSummaryRecordRepository summaryRepository;
//     private final ProductivityMetricRecordRepository metricRepository;

//     public TeamSummaryServiceImpl(TeamSummaryRecordRepository summaryRepository,
//                                 ProductivityMetricRecordRepository metricRepository) {
//         this.summaryRepository = summaryRepository;
//         this.metricRepository = metricRepository;
//     }

//     @Override
//     public TeamSummaryRecord generateSummary(String teamName, LocalDate summaryDate) {
//         List<ProductivityMetricRecord> teamMetrics = metricRepository.findAll().stream()
//                 .filter(metric -> metric.getDate().equals(summaryDate))
//                 .collect(Collectors.toList());

//         if (teamMetrics.isEmpty()) {
//             return summaryRepository.save(new TeamSummaryRecord(teamName, summaryDate, 0.0, 0.0, 0.0, 0));
//         }

//         double avgHours = teamMetrics.stream()
//                 .mapToDouble(ProductivityMetricRecord::getHoursLogged)
//                 .average().orElse(0.0);
        
//         double avgTasks = teamMetrics.stream()
//                 .mapToDouble(ProductivityMetricRecord::getTasksCompleted)
//                 .average().orElse(0.0);
        
//         double avgScore = teamMetrics.stream()
//                 .mapToDouble(ProductivityMetricRecord::getProductivityScore)
//                 .average().orElse(0.0);

//         int anomalyCount = 0;

//         TeamSummaryRecord summary = new TeamSummaryRecord(teamName, summaryDate, avgHours, avgTasks, avgScore, anomalyCount);
//         return summaryRepository.save(summary);
//     }

//     @Override
//     public List<TeamSummaryRecord> getSummariesByTeam(String teamName) {
//         return summaryRepository.findByTeamName(teamName);
//     }

//     @Override
//     public List<TeamSummaryRecord> getAllSummaries() {
//         return summaryRepository.findAll();
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.repository.TeamSummaryRecordRepository;
import com.example.demo.service.TeamSummaryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamSummaryServiceImpl implements TeamSummaryService {

    private final TeamSummaryRecordRepository summaryRepository;
    private final ProductivityMetricRecordRepository metricRepository;

    public TeamSummaryServiceImpl(TeamSummaryRecordRepository summaryRepository,
                                  ProductivityMetricRecordRepository metricRepository) {
        this.summaryRepository = summaryRepository;
        this.metricRepository = metricRepository;
    }

    @Override
    public TeamSummaryRecord generateSummary(String teamName, LocalDate summaryDate) {
        List<ProductivityMetricRecord> metrics = metricRepository.findAll().stream()
                .filter(m -> teamName.equals(m.getEmployeeId().toString())) // Assuming employeeId relates to team, but simplified
                .collect(Collectors.toList());

        double avgHours = metrics.stream().mapToDouble(ProductivityMetricRecord::getHoursLogged).average().orElse(0);
        double avgTasks = metrics.stream().mapToDouble(ProductivityMetricRecord::getTasksCompleted).average().orElse(0);
        double avgScore = metrics.stream().mapToDouble(ProductivityMetricRecord::getProductivityScore).average().orElse(0);
        int anomalyCount = 0; // Simplified

        TeamSummaryRecord summary = new TeamSummaryRecord();
        summary.setTeamName(teamName);
        summary.setSummaryDate(summaryDate);
        summary.setAvgHoursLogged(avgHours);
        summary.setAvgTasksCompleted(avgTasks);
        summary.setAvgScore(avgScore);
        summary.setAnomalyCount(anomalyCount);

        return summaryRepository.save(summary);
    }

    @Override
    public List<TeamSummaryRecord> getSummariesByTeam(String teamName) {
        return summaryRepository.findByTeamName(teamName);
    }

    @Override
    public List<TeamSummaryRecord> getAllSummaries() {
        return summaryRepository.findAll();
    }
}