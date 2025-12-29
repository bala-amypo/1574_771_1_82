// package com.example.demo.model;

// import javax.persistence.*;
// import java.time.LocalDateTime;
// import java.util.List;

// @Entity
// @Table(name = "employee_profiles")
// public class EmployeeProfile {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(unique = true, nullable = false)
//     private String employeeId;

//     @Column(nullable = false)
//     private String fullName;

//     @Column(unique = true, nullable = false)
//     private String email;

//     private String teamName;
//     private String roles;
//     private Boolean active = true;

//     @Column(name = "created_at")
//     private LocalDateTime createdAt;

//     @OneToMany(mappedBy = "employeeId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     private List<ProductivityMetricRecord> metrics;

//     @OneToMany(mappedBy = "employeeId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     private List<Credential> credentials;

//     public EmployeeProfile() {}

//     public EmployeeProfile(String employeeId, String fullName, String email, String teamName, String roles) {
//         this.employeeId = employeeId;
//         this.fullName = fullName;
//         this.email = email;
//         this.teamName = teamName;
//         this.roles = roles;
//         this.active = true;
//     }

//     @PrePersist
//     protected void onCreate() {
//         createdAt = LocalDateTime.now();
//     }

//     // Getters and Setters
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

//     public String getRole() { return roles; }
//     public void setRole(String roles) { this.roles = roles; }

//     public Boolean getActive() { return active; }
//     public void setActive(Boolean active) { this.active = active; }

//     public LocalDateTime getCreatedAt() { return createdAt; }
//     public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

//     public List<ProductivityMetricRecord> getMetrics() { return metrics; }
//     public void setMetrics(List<ProductivityMetricRecord> metrics) { this.metrics = metrics; }

//     public List<Credential> getCredentials() { return credentials; }
//     public void setCredentials(List<Credential> credentials) { this.credentials = credentials; }
// }
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "employee_profiles")
public class EmployeeProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String employeeId;

    private String fullName;
    private String email;
    private String teamName;
    private String role;
    private Boolean active = true;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    // Constructors
    public EmployeeProfile() {}

    public EmployeeProfile(String employeeId, String fullName, String email, String teamName, String role, Boolean active) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.teamName = teamName;
        this.role = role;
        this.active = active;
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