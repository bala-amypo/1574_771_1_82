// package com.example.demo.model;

// import javax.persistence.*;

// @Entity
// @Table(name = "anomaly_rules")
// public class AnomalyRule {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name = "rule_code", unique = true, nullable = false)
//     private String ruleCode;

//     private String description;

//     @Column(name = "threshold_type")
//     private String thresholdType;

//     @Column(name = "threshold_value")
//     private Double thresholdValue;

//     private Boolean active = true;

//     public AnomalyRule() {}

//     public AnomalyRule(String ruleCode, String description, String thresholdType, Double thresholdValue) {
//         this.ruleCode = ruleCode;
//         this.description = description;
//         this.thresholdType = thresholdType;
//         this.thresholdValue = thresholdValue;
//         this.active = true;
//     }

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getRuleCode() { return ruleCode; }
//     public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }

//     public String getDescription() { return description; }
//     public void setDescription(String description) { this.description = description; }

//     public String getThresholdType() { return thresholdType; }
//     public void setThresholdType(String thresholdType) { this.thresholdType = thresholdType; }

//     public Double getThresholdValue() { return thresholdValue; }
//     public void setThresholdValue(Double thresholdValue) { this.thresholdValue = thresholdValue; }

//     public Boolean getActive() { return active; }
//     public void setActive(Boolean active) { this.active = active; }
// }
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

    private String description;
    private String thresholdType;
    private Double thresholdValue;
    private Boolean active = true;

    // Constructors
    public AnomalyRule() {}

    public AnomalyRule(String ruleCode, String description, String thresholdType, Double thresholdValue, Boolean active) {
        this.ruleCode = ruleCode;
        this.description = description;
        this.thresholdType = thresholdType;
        this.thresholdValue = thresholdValue;
        this.active = active;
    }

    // Getters and Setters
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