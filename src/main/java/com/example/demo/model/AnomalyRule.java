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

    private boolean active;

    private Double thresholdValue;

    // ===== DEFAULT CONSTRUCTOR (JPA requires this) =====
    public AnomalyRule() {
    }

    // ===== PARAMETERIZED CONSTRUCTOR =====
    public AnomalyRule(Long id, String ruleCode, boolean active, Double thresholdValue) {
        this.id = id;
        this.ruleCode = ruleCode;
        this.active = active;
        this.thresholdValue = thresholdValue;
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Double getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(Double thresholdValue) {
        this.thresholdValue = thresholdValue;
    }
}
