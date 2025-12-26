package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "credentials",
    uniqueConstraints = @UniqueConstraint(columnNames = "credentialId")
)
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String credentialId;
    private String issuer;
    private String status = "PENDING";

    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;

    @Column(columnDefinition = "TEXT")
    private String metadataJson;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeProfile employee;

    public Credential() {}

    @PrePersist
    public void onCreate() {
        this.issuedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "PENDING";
        }
    }

    public Long getId() {
        return id;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getStatus() {
        return status;
    }

    public boolean isVerified() {
        return "VERIFIED".equalsIgnoreCase(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getMetadataJson() {
        return metadataJson;
    }

    public void setMetadataJson(String metadataJson) {
        this.metadataJson = metadataJson;
    }

    public EmployeeProfile getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeProfile employee) {
        this.employee = employee;
    }

    /* 🔥 AMYPO TEST CRITICAL METHOD */
    public Long getEmployeeId() {
        return employee != null ? employee.getId() : null;
    }
}
