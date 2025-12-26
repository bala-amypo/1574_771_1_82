package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.model.AnomalyRule;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import com.example.demo.repository.AnomalyRuleRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.ProductivityMetricService;
import com.example.demo.util.ProductivityCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository metricRepo;
    private final EmployeeProfileRepository employeeRepo;
    private final AnomalyRuleRepository ruleRepo;
    private final AnomalyFlagRecordRepository flagRepo;

    public ProductivityMetricServiceImpl(ProductivityMetricRecordRepository metricRepo,
                                         EmployeeProfileRepository employeeRepo,
                                         AnomalyRuleRepository ruleRepo,
                                         AnomalyFlagRecordRepository flagRepo) {
        this.metricRepo = metricRepo;
        this.employeeRepo = employeeRepo;
        this.ruleRepo = ruleRepo;
        this.flagRepo = flagRepo;
    }

    @Override
    @Transactional
    public ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric) {
        Long empId = metric.getEmployeeId();
        if (empId == null) {
            throw new ResourceNotFoundException("Employee not found");
        }
        EmployeeProfile e = employeeRepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (!Boolean.TRUE.equals(e.getActive())) {
            throw new IllegalStateException("Employee not active");
        }
        // uniqueness: one per employee per date
        if (metricRepo.findByEmployeeIdAndDate(empId, metric.getDate()).isPresent()) {
            throw new IllegalStateException("Metric exists");
        }

        double score = ProductivityCalculator.computeScore(metric.getHoursLogged() == null ? 0.0 : metric.getHoursLogged(),
                metric.getTasksCompleted() == null ? 0 : metric.getTasksCompleted(),
                metric.getMeetingsAttended() == null ? 0 : metric.getMeetingsAttended());

        metric.setProductivityScore(score);
        metric.setSubmittedAt(LocalDateTime.now());
        metric.setEmployeeProfile(e);

        ProductivityMetricRecord saved = metricRepo.save(metric);

        // evaluate rules and create flags
        List<AnomalyRule> activeRules = ruleRepo.findByActiveTrue();
        for (AnomalyRule r : activeRules) {
            if ("SCORE_BELOW".equalsIgnoreCase(r.getThresholdType())) {
                if (r.getThresholdValue() != null && saved.getProductivityScore() < r.getThresholdValue()) {
                    AnomalyFlagRecord flag = new AnomalyFlagRecord();
                    flag.setEmployeeId(e.getId());
                    flag.setEmployeeProfile(e);
                    flag.setMetric(saved);
                    flag.setRuleCode(r.getRuleCode());
                    flag.setSeverity("MEDIUM");
                    flag.setDetails("Score below threshold");
                    flag.setFlaggedAt(LocalDateTime.now());
                    flag.setResolved(false);
                    flagRepo.save(flag);
                }
            }
        }
        return saved;
    }

    @Override
    public ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updated) {
        ProductivityMetricRecord existing = metricRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Metric not found"));
        if (updated.getHoursLogged() != null) existing.setHoursLogged(Math.max(0.0, updated.getHoursLogged()));
        if (updated.getTasksCompleted() != null) existing.setTasksCompleted(Math.max(0, updated.getTasksCompleted()));
        if (updated.getMeetingsAttended() != null) existing.setMeetingsAttended(Math.max(0, updated.getMeetingsAttended()));
        double score = ProductivityCalculator.computeScore(existing.getHoursLogged(), existing.getTasksCompleted(), existing.getMeetingsAttended());
        existing.setProductivityScore(score);
        return metricRepo.save(existing);
    }

    @Override
    public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {
        return metricRepo.findByEmployeeId(employeeId);
    }

    @Override
    public java.util.Optional<ProductivityMetricRecord> getMetricById(Long id) {
        return metricRepo.findById(id);
    }

    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return metricRepo.findAll();
    }
}