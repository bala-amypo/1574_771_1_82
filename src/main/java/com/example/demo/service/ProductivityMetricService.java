package com.example.demo.service;

import com.example.demo.model.ProductivityMetricRecord;
import java.util.List;
import java.util.Optional;

public interface ProductivityMetricService {

    ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric);

    ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord metric);

    List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId);

    Optional<ProductivityMetricRecord> getMetricById(Long id);

    List<ProductivityMetricRecord> getAllMetrics();
}
