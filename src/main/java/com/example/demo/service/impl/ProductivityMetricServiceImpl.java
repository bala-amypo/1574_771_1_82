package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ProductivityMetricService;
import com.example.demo.util.ProductivityCalculator;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository metricRepo;
    private final EmployeeProfileRepository empRepo;
    private final AnomalyRuleRepository ruleRepo;
    private final AnomalyFlagRecordRepository flagRepo;

    public ProductivityMetricServiceImpl(
            ProductivityMetricRecordRepository metricRepo,
            EmployeeProfileRepository empRepo,
            AnomalyRuleRepository ruleRepo,
            AnomalyFlagRecordRepository flagRepo) {
        this.metricRepo = metricRepo;
        this.empRepo = empRepo;
        this.ruleRepo = ruleRepo;
        this.flagRepo = flagRepo;
    }

    public ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric) {

        EmployeeProfile emp = empRepo.findById(metric.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (!emp.getActive())
            throw new IllegalStateException("Employee not active");

        boolean exists = metricRepo.findByEmployeeId(metric.getEmployeeId())
                .stream()
                .anyMatch(m -> m.getDate().equals(metric.getDate()));

        if (exists)
            throw new IllegalStateException("Metric already exists");

        double score = ProductivityCalculator.computeScore(
                metric.getHoursLogged(),
                metric.getTasksCompleted(),
                metric.getMeetingsAttended());

        metric.setProductivityScore(score);

        ProductivityMetricRecord saved = metricRepo.save(metric);

        ruleRepo.findByActiveTrue().forEach(rule -> {
            if (score < rule.getThresholdValue()) {
                AnomalyFlagRecord f = new AnomalyFlagRecord();
                f.setEmployeeId(metric.getEmployeeId());
                f.setMetricId(saved.getId());
                f.setRuleCode(rule.getRuleCode());
                f.setResolved(false);
                flagRepo.save(f);
            }
        });

        return saved;
    }

    public ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updated) {
        ProductivityMetricRecord existing = metricRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metric not found"));

        existing.setHoursLogged(updated.getHoursLogged());
        existing.setTasksCompleted(updated.getTasksCompleted());
        existing.setMeetingsAttended(updated.getMeetingsAttended());

        double score = ProductivityCalculator.computeScore(
                existing.getHoursLogged(),
                existing.getTasksCompleted(),
                existing.getMeetingsAttended());

        existing.setProductivityScore(score);
        return metricRepo.save(existing);
    }

    public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {
        return metricRepo.findByEmployeeId(employeeId);
    }

    public Optional<ProductivityMetricRecord> getMetricById(Long id) {
        return metricRepo.findById(id);
    }

    public List<ProductivityMetricRecord> getAllMetrics() {
        return metricRepo.findAll();
    }
}
