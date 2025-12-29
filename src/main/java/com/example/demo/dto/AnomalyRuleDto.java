// package com.example.demo.dto;

// public class AnomalyRuleDto {
//     private Long id;
//     private String ruleCode;
//     private String description;
//     private String thresholdType;
//     private Double thresholdValue;
//     private Boolean active;

//     public AnomalyRuleDto() {}

//     public AnomalyRuleDto(Long id, String ruleCode, String description, String thresholdType, 
//                          Double thresholdValue, Boolean active) {
//         this.id = id;
//         this.ruleCode = ruleCode;
//         this.description = description;
//         this.thresholdType = thresholdType;
//         this.thresholdValue = thresholdValue;
//         this.active = active;
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

package com.example.demo.dto;

public class AnomalyRuleDto {
    private Long id;
    private String ruleCode;
    private String description;
    private String thresholdType;
    private Double thresholdValue;
    private Boolean active;

    public AnomalyRuleDto() {}

    public AnomalyRuleDto(Long id, String ruleCode, String description, String thresholdType, Double thresholdValue, Boolean active) {
        this.id = id;
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