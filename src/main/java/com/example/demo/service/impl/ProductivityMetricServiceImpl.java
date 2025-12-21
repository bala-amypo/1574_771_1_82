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

    public ProductivityMetricRecord recordMetric(ProductivityMetricRecord m) {

        EmployeeProfile e = employeeRepo.findById(m.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (!e.getActive()) throw new IllegalStateException("Employee not active");

        boolean exists = metricRepo.findByEmployeeId(m.getEmployeeId())
                .stream()
                .anyMatch(x -> x.getDate().equals(m.getDate()));

        if (exists) throw new IllegalStateException("Metric already exists");

        double score = ProductivityCalculator.computeScore(
                Math.max(0, m.getHoursLogged()),
                Math.max(0, m.getTasksCompleted()),
                Math.max(0, m.getMeetingsAttended())
        );

        m.setProductivityScore(score);
        ProductivityMetricRecord saved = metricRepo.save(m);

        ruleRepo.findByActiveTrue().forEach(r -> {
            if (score < r.getThresholdValue()) {
                AnomalyFlagRecord f = new AnomalyFlagRecord();
                f.setEmployeeId(m.getEmployeeId());
                f.setMetricId(saved.getId());
                f.setRuleCode(r.getRuleCode());
                f.setSeverity("LOW");
                f.setDetails("Threshold breached");
                flagRepo.save(f);
            }
        });

        return saved;
    }

    public ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord m) {
        ProductivityMetricRecord old = metricRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metric not found"));
        m.setId(old.getId());
        return recordMetric(m);
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
