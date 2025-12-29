// package com.example.demo.repository;

// import com.example.demo.model.ProductivityMetricRecord;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public interface ProductivityMetricRecordRepository extends JpaRepository<ProductivityMetricRecord, Long> {
//     List<ProductivityMetricRecord> findByEmployeeId(Long employeeId);
// }
package com.example.demo.repository;

import com.example.demo.model.ProductivityMetricRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductivityMetricRecordRepository extends JpaRepository<ProductivityMetricRecord, Long> {
    List<ProductivityMetricRecord> findByEmployeeId(Long employeeId);
}