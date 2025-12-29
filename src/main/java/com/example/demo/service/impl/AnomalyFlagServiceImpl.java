// package com.example.demo.service.impl;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.AnomalyFlagRecord;
// import com.example.demo.repository.AnomalyFlagRecordRepository;
// import com.example.demo.service.AnomalyFlagService;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// public class AnomalyFlagServiceImpl implements AnomalyFlagService {

//     private final AnomalyFlagRecordRepository anomalyFlagRepository;

//     public AnomalyFlagServiceImpl(AnomalyFlagRecordRepository anomalyFlagRepository) {
//         this.anomalyFlagRepository = anomalyFlagRepository;
//     }

//     @Override
//     public AnomalyFlagRecord flagAnomaly(AnomalyFlagRecord flag) {
//         flag.setResolved(false);
//         return anomalyFlagRepository.save(flag);
//     }

//     @Override
//     public AnomalyFlagRecord resolveFlag(Long id) {
//         AnomalyFlagRecord flag = anomalyFlagRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Anomaly flag not found"));
        
//         flag.setResolved(true);
//         return anomalyFlagRepository.save(flag);
//     }

//     @Override
//     public List<AnomalyFlagRecord> getFlagsByEmployee(Long employeeId) {
//         return anomalyFlagRepository.findAll().stream()
//                 .filter(flag -> flag.getEmployeeId().equals(employeeId))
//                 .collect(Collectors.toList());
//     }

//     @Override
//     public List<AnomalyFlagRecord> getFlagsByMetric(Long metricId) {
//         return anomalyFlagRepository.findByMetricId(metricId);
//     }

//     @Override
//     public List<AnomalyFlagRecord> getAllFlags() {
//         return anomalyFlagRepository.findAll();
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import com.example.demo.service.AnomalyFlagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomalyFlagServiceImpl implements AnomalyFlagService {

    private final AnomalyFlagRecordRepository flagRepository;

    public AnomalyFlagServiceImpl(AnomalyFlagRecordRepository flagRepository) {
        this.flagRepository = flagRepository;
    }

    @Override
    public AnomalyFlagRecord flagAnomaly(AnomalyFlagRecord flag) {
        flag.setResolved(false);
        return flagRepository.save(flag);
    }

    @Override
    public AnomalyFlagRecord resolveFlag(Long id) {
        AnomalyFlagRecord flag = flagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anomaly flag not found"));
        flag.setResolved(true);
        return flagRepository.save(flag);
    }

    @Override
    public List<AnomalyFlagRecord> getFlagsByEmployee(Long employeeId) {
        return flagRepository.findAll().stream()
                .filter(f -> employeeId.equals(f.getEmployeeId()))
                .toList();
    }

    @Override
    public List<AnomalyFlagRecord> getFlagsByMetric(Long metricId) {
        return flagRepository.findByMetricId(metricId);
    }

    @Override
    public List<AnomalyFlagRecord> getAllFlags() {
        return flagRepository.findAll();
    }
}