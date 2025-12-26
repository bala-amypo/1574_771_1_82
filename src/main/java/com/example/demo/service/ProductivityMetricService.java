package com.example.demo.service;

import com.example.demo.model.ProductivityMetricRecord;

import java.util.List;
import java.util.Optional;

public interface ProductivityMetricService {

    ProductivityMetricRecord recordMetric(ProductivityMetricRecord record);

    Optional<ProductivityMetricRecord> getMetricById(Long id);

    List<ProductivityMetricRecord> getAllMetrics();
}
