// package com.example.demo.service.impl;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.*;
// import com.example.demo.repository.*;
// import com.example.demo.service.ProductivityMetricService;
// import com.example.demo.util.ProductivityCalculator;
// import org.springframework.dao.DataIntegrityViolationException;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class ProductivityMetricServiceImpl implements ProductivityMetricService {

//     private final ProductivityMetricRecordRepository metricRepository;
//     private final EmployeeProfileRepository employeeRepository;
//     private final AnomalyRuleRepository ruleRepository;
//     private final AnomalyFlagRecordRepository flagRepository;

//     public ProductivityMetricServiceImpl(ProductivityMetricRecordRepository metricRepository,
//                                        EmployeeProfileRepository employeeRepository,
//                                        AnomalyRuleRepository ruleRepository,
//                                        AnomalyFlagRecordRepository flagRepository) {
//         this.metricRepository = metricRepository;
//         this.employeeRepository = employeeRepository;
//         this.ruleRepository = ruleRepository;
//         this.flagRepository = flagRepository;
//     }

//     @Override
//     public ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric) {
//         EmployeeProfile employee = employeeRepository.findById(metric.getEmployeeId())
//                 .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        
//         if (!employee.getActive()) {
//             throw new ResourceNotFoundException("Employee not found");
//         }

//         double hours = Math.max(0, metric.getHoursLogged() != null ? metric.getHoursLogged() : 0);
//         int tasks = Math.max(0, metric.getTasksCompleted() != null ? metric.getTasksCompleted() : 0);
//         int meetings = Math.max(0, metric.getMeetingsAttended() != null ? metric.getMeetingsAttended() : 0);
        
//         metric.setHoursLogged(hours);
//         metric.setTasksCompleted(tasks);
//         metric.setMeetingsAttended(meetings);
//         metric.setProductivityScore(ProductivityCalculator.computeScore(hours, tasks, meetings));

//         try {
//             ProductivityMetricRecord saved = metricRepository.save(metric);
//             checkAnomalies(saved);
//             return saved;
//         } catch (DataIntegrityViolationException e) {
//             throw new IllegalStateException("Metric already exists for this employee and date");
//         }
//     }

//     @Override
//     public ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updated) {
//         ProductivityMetricRecord existing = getMetricById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Metric not found"));
        
//         if (updated.getHoursLogged() != null) {
//             existing.setHoursLogged(Math.max(0, updated.getHoursLogged()));
//         }
//         if (updated.getTasksCompleted() != null) {
//             existing.setTasksCompleted(Math.max(0, updated.getTasksCompleted()));
//         }
//         if (updated.getMeetingsAttended() != null) {
//             existing.setMeetingsAttended(Math.max(0, updated.getMeetingsAttended()));
//         }
//         if (updated.getRawDataJson() != null) {
//             existing.setRawDataJson(updated.getRawDataJson());
//         }
        
//         existing.setProductivityScore(ProductivityCalculator.computeScore(
//                 existing.getHoursLogged(), existing.getTasksCompleted(), existing.getMeetingsAttended()));
        
//         ProductivityMetricRecord saved = metricRepository.save(existing);
//         checkAnomalies(saved);
//         return saved;
//     }

//     @Override
//     public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {
//         return metricRepository.findByEmployeeId(employeeId);
//     }

//     @Override
//     public Optional<ProductivityMetricRecord> getMetricById(Long id) {
//         return metricRepository.findById(id);
//     }

//     @Override
//     public List<ProductivityMetricRecord> getAllMetrics() {
//         return metricRepository.findAll();
//     }

//     private void checkAnomalies(ProductivityMetricRecord metric) {
//         List<AnomalyRule> activeRules = ruleRepository.findByActiveTrue();
        
//         for (AnomalyRule rule : activeRules) {
//             boolean triggered = false;
//             String severity = "MEDIUM";
//             String details = "";
            
//             if ("SCORE_BELOW".equals(rule.getThresholdType())) {
//                 if (metric.getProductivityScore() < rule.getThresholdValue()) {
//                     triggered = true;
//                     details = "Productivity score " + metric.getProductivityScore() + " is below threshold " + rule.getThresholdValue();
//                 }
//             }
            
//             if (triggered) {
//                 AnomalyFlagRecord flag = new AnomalyFlagRecord(
//                         metric.getEmployeeId(), metric.getId(), rule.getRuleCode(), severity, details);
//                 flagRepository.save(flag);
//             }
//         }
//     }
// }
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository metricRepository;
    private final EmployeeProfileRepository employeeRepository;
    private final AnomalyRuleRepository ruleRepository;
    private final AnomalyFlagRecordRepository flagRepository;

    public ProductivityMetricServiceImpl(ProductivityMetricRecordRepository metricRepository,
                                         EmployeeProfileRepository employeeRepository,
                                         AnomalyRuleRepository ruleRepository,
                                         AnomalyFlagRecordRepository flagRepository) {
        this.metricRepository = metricRepository;
        this.employeeRepository = employeeRepository;
        this.ruleRepository = ruleRepository;
        this.flagRepository = flagRepository;
    }

    @Override
    public ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric) {
        // Check employee exists and active
        EmployeeProfile employee = employeeRepository.findById(metric.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (!employee.getActive()) {
            throw new ResourceNotFoundException("Employee not found");
        }

        // Check for duplicate metric for same employee and date
        List<ProductivityMetricRecord> existing = metricRepository.findByEmployeeId(metric.getEmployeeId());
        boolean duplicate = existing.stream()
                .anyMatch(m -> m.getDate().equals(metric.getDate()));
        if (duplicate) {
            throw new IllegalStateException("Metric already exists for this employee and date");
        }

        // Compute score
        double score = ProductivityCalculator.computeScore(metric.getHoursLogged(), metric.getTasksCompleted(), metric.getMeetingsAttended());
        metric.setProductivityScore(score);

        ProductivityMetricRecord saved = metricRepository.save(metric);

        // Check for anomalies
        List<AnomalyRule> activeRules = ruleRepository.findByActiveTrue();
        for (AnomalyRule rule : activeRules) {
            if ("SCORE_BELOW".equals(rule.getThresholdType()) && score < rule.getThresholdValue()) {
                AnomalyFlagRecord flag = new AnomalyFlagRecord();
                flag.setEmployeeId(metric.getEmployeeId());
                flag.setMetricId(saved.getId());
                flag.setRuleCode(rule.getRuleCode());
                flag.setSeverity("LOW");
                flag.setDetails("Score below threshold");
                flagRepository.save(flag);
            }
        }

        return saved;
    }

    @Override
    public ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updated) {
        ProductivityMetricRecord existing = metricRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Metric not found"));
        existing.setHoursLogged(updated.getHoursLogged());
        existing.setTasksCompleted(updated.getTasksCompleted());
        existing.setMeetingsAttended(updated.getMeetingsAttended());
        double score = ProductivityCalculator.computeScore(updated.getHoursLogged(), updated.getTasksCompleted(), updated.getMeetingsAttended());
        existing.setProductivityScore(score);
        return metricRepository.save(existing);
    }

    @Override
    public List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId) {
        return metricRepository.findByEmployeeId(employeeId);
    }

    @Override
    public Optional<ProductivityMetricRecord> getMetricById(Long id) {
        return metricRepository.findById(id);
    }

    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return metricRepository.findAll();
    }
}