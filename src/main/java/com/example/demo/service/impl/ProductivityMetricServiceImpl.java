package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ProductivityMetricService;
import com.example.demo.util.ProductivityCalculator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository metricRepo;
    private final EmployeeProfileRepository employeeRepo;
    private final AnomalyRuleRepository ruleRepo;
    private final AnomalyFlagRecordRepository flagRepo;

    public ProductivityMetricServiceImpl(
            ProductivityMetricRecordRepository metricRepo,
            EmployeeProfileRepository employeeRepo,
            AnomalyRuleRepository ruleRepo,
            AnomalyFlagRecordRepository flagRepo) {

        this.metricRepo = metricRepo;
        this.employeeRepo = employeeRepo;
        this.ruleRepo = ruleRepo;
        this.flagRepo = flagRepo;
    }

    @Override
    public ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric) {

        EmployeeProfile employee = employeeRepo.findById(metric.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (!employee.getActive())
            throw new ResourceNotFoundException("Employee not found");

        metric.setEmployee(employee);

        double score = ProductivityCalculator.computeScore(
                metric.getHoursLogged(),
                metric.getTasksCompleted(),
                metric.getMeetingsAttended()
        );

        metric.setProductivityScore(score);

        ProductivityMetricRecord saved = metricRepo.save(metric);

        ruleRepo.findByActiveTrue().forEach(rule -> {
            if (score < rule.getThresholdValue()) {
                AnomalyFlagRecord flag = new AnomalyFlagRecord();
                flag.setEmployee(employee);
                flag.setMetric(saved);
                flag.setRuleCode(rule.getRuleCode());
                flag.setSeverity("LOW");
                flag.setDetails("Threshold breached");
                flagRepo.save(flag);
            }
        });

        return saved;
    }

    @Override
    public ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updated) {
        ProductivityMetricRecord existing = metricRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metric not found"));

        existing.setHoursLogged(updated.getHoursLogged());
        existing.setTasksCompleted(updated.getTasksCompleted());
        existing.setMeetingsAttended(updated.getMeetingsAttended());

        double score = ProductivityCalculator.computeScore(
                updated.getHoursLogged(),
                updated.getTasksCompleted(),
                updated.getMeetingsAttended()
        );

        existing.setProductivityScore(score);
        return metricRepo.save(existing);
    }

    @Override
    public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {
        return metricRepo.findByEmployeeId(employeeId);
    }

    @Override
    public Optional<ProductivityMetricRecord> getMetricById(Long id) {
        return metricRepo.findById(id);
    }

    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return metricRepo.findAll();
    }
}
