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

    @Override
    public AnomalyFlagRecord flagAnomaly(AnomalyFlagRecord flag) {
        flag.setResolved(false);
        flag.setFlaggedAt(java.time.LocalDateTime.now());
        return repo.save(flag);
    }

    @Override
    public AnomalyFlagRecord resolveFlag(Long id) {
        return repo.findById(id).map(f -> {
            f.setResolved(true);
            return repo.save(f);
        }).orElseThrow(() -> new ResourceNotFoundException("Anomaly flag not found"));
    }

    @Override
    public List<AnomalyFlagRecord> getFlagsByEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }

    @Override
    public List<AnomalyFlagRecord> getFlagsByMetric(Long metricId) {
        return repo.findByMetricId(metricId);
    }

    @Override
    public List<AnomalyFlagRecord> getAllFlags() {
        return repo.findAll();
    }
}