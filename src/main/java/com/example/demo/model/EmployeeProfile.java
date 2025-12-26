package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee_profiles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"employeeId"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class EmployeeProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String employeeId;

    private String fullName;

    @Column(nullable = false)
    private String email;

    private String teamName;

    private String role;

    private Boolean active = true;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "metric", cascade = CascadeType.ALL)
    private List<ProductivityMetricRecord> metrics = new ArrayList<>();

    @OneToMany(mappedBy = "employeeProfile", cascade = CascadeType.ALL)
    private List<Credential> credentials = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public EmployeeProfile() {}

    public EmployeeProfile(String employeeId, String fullName, String email) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.active = true;
    }

    // getters/setters
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
    public List<ProductivityMetricRecord> getMetrics() { return metrics; }
    public List<Credential> getCredentials() { return credentials; }
}