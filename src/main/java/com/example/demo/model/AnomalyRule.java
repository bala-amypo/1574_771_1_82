package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "anomaly_rules", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ruleCode"})
})
public class AnomalyRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleCode;
    @Lob
    private String description;
    private String thresholdType;
    private Double thresholdValue;
    private Boolean active = true;

    public AnomalyRule() {}

    public AnomalyRule(String ruleCode, String description) {
        this.ruleCode = ruleCode;
        this.description = description;
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getThresholdType() { return thresholdType; }
    public void setThresholdType(String thresholdType) { this.thresholdType = thresholdType; }
    public Double getThresholdValue() { return thresholdValue; }
    public void setThresholdValue(Double thresholdValue) { this.thresholdValue = thresholdValue; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}