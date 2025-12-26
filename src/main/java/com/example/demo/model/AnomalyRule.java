package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "anomaly_rules")
public class AnomalyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ruleCode;

    private Double thresholdValue;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

    public Double getThresholdValue() { return thresholdValue; }
    public void setThresholdValue(Double thresholdValue) { this.thresholdValue = thresholdValue; }
}
