package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import com.example.demo.service.AnomalyFlagService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnomalyFlagServiceImpl implements AnomalyFlagService {

    private final AnomalyFlagRecordRepository repo;

    public AnomalyFlagServiceImpl(AnomalyFlagRecordRepository repo) {
        this.repo = repo;
    }

    public AnomalyFlagRecord flagAnomaly(AnomalyFlagRecord flag) {
        flag.setResolved(false);
        return repo.save(flag);
    }

    public AnomalyFlagRecord resolveFlag(Long id) {
        AnomalyFlagRecord f = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anomaly flag not found"));
        f.setResolved(true);
        return repo.save(f);
    }

    public List<AnomalyFlagRecord> getFlagsByEmployee(Long employeeId) {
        return repo.findAll().stream().filter(f -> f.getEmployeeId().equals(employeeId)).toList();
    }

    public List<AnomalyFlagRecord> getFlagsByMetric(Long metricId) {
        return repo.findByMetricId(metricId);
    }

    public List<AnomalyFlagRecord> getAllFlags() {
        return repo.findAll();
    }
}
