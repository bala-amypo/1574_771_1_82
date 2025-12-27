package com.example.demo.service;

import com.example.demo.model.ProductivityMetricRecord;
import java.util.*;

public interface ProductivityMetricService {
    ProductivityMetricRecord recordMetric(ProductivityMetricRecord metric);
    ProductivityMetricRecord updateMetric(Long id, ProductivityMetricRecord updated);
    List<ProductivityMetricRecord> getMetricsByEmployee(Long employeeId);
    Optional<ProductivityMetricRecord> getMetricById(Long id);
    List<ProductivityMetricRecord> getAllMetrics();
}
