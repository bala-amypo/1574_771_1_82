// package com.example.demo.dto;

// import java.time.LocalDateTime;

// public class EmployeeProfileDto {
//     private Long id;
//     private String employeeId;
//     private String fullName;
//     private String email;
//     private String teamName;
//     private String roles;
//     private Boolean active;
//     private LocalDateTime createdAt;

//     public EmployeeProfileDto() {}

//     public EmployeeProfileDto(Long id, String employeeId, String fullName, String email, 
//                             String teamName, String roles, Boolean active, LocalDateTime createdAt) {
//         this.id = id;
//         this.employeeId = employeeId;
//         this.fullName = fullName;
//         this.email = email;
//         this.teamName = teamName;
//         this.roles = roles;
//         this.active = active;
//         this.createdAt = createdAt;
//     }

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getEmployeeId() { return employeeId; }
//     public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

//     public String getFullName() { return fullName; }
//     public void setFullName(String fullName) { this.fullName = fullName; }

//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }

//     public String getTeamName() { return teamName; }
//     public void setTeamName(String teamName) { this.teamName = teamName; }

//     public String getRoles() { return roles; }
//     public void setRoles(String roles) { this.roles = roles; }

//     public Boolean getActive() { return active; }
//     public void setActive(Boolean active) { this.active = active; }

//     public LocalDateTime getCreatedAt() { return createdAt; }
//     public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
// }
package com.example.demo.dto;

import java.time.LocalDateTime;

public class EmployeeProfileDto {
    private Long id;
    private String employeeId;
    private String fullName;
    private String email;
    private String teamName;
    private String role;
    private Boolean active;
    private LocalDateTime createdAt;

    public EmployeeProfileDto() {}

    public EmployeeProfileDto(Long id, String employeeId, String fullName, String email, String teamName, String role, Boolean active, LocalDateTime createdAt) {
        this.id = id;
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.teamName = teamName;
        this.role = role;
        this.active = active;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}