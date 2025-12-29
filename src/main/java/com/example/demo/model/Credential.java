// package com.example.demo.model;

// import javax.persistence.*;
// import java.time.LocalDateTime;
// import java.util.List;

// @Entity
// @Table(name = "credentials")
// public class Credential {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name = "employee_id", nullable = false)
//     private Long employeeId;

//     @Column(name = "credential_id", unique = true, nullable = false)
//     private String credentialId;

//     private String issuer;

//     @Column(name = "issued_at")
//     private LocalDateTime issuedAt;

//     @Column(name = "expires_at")
//     private LocalDateTime expiresAt;

//     private String status = "PENDING";

//     @Column(name = "metadata_json", columnDefinition = "TEXT")
//     private String metadataJson;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "employee_id", insertable = false, updatable = false)
//     private EmployeeProfile employee;

//     @OneToMany(mappedBy = "credentialId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     private List<CredentialVerificationEvent> verificationEvents;

//     public Credential() {}

//     public Credential(Long employeeId, String credentialId, String issuer, LocalDateTime issuedAt, LocalDateTime expiresAt) {
//         this.employeeId = employeeId;
//         this.credentialId = credentialId;
//         this.issuer = issuer;
//         this.issuedAt = issuedAt;
//         this.expiresAt = expiresAt;
//         this.status = "PENDING";
//     }

//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public Long getEmployeeId() { return employeeId; }
//     public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

//     public String getCredentialId() { return credentialId; }
//     public void setCredentialId(String credentialId) { this.credentialId = credentialId; }

//     public String getIssuer() { return issuer; }
//     public void setIssuer(String issuer) { this.issuer = issuer; }

//     public LocalDateTime getIssuedAt() { return issuedAt; }
//     public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }

//     public LocalDateTime getExpiresAt() { return expiresAt; }
//     public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }

//     public String getStatus() { return status; }
//     public void setStatus(String status) { this.status = status; }

//     public String getMetadataJson() { return metadataJson; }
//     public void setMetadataJson(String metadataJson) { this.metadataJson = metadataJson; }

//     public EmployeeProfile getEmployee() { return employee; }
//     public void setEmployee(EmployeeProfile employee) { this.employee = employee; }

//     public List<CredentialVerificationEvent> getVerificationEvents() { return verificationEvents; }
//     public void setVerificationEvents(List<CredentialVerificationEvent> verificationEvents) { this.verificationEvents = verificationEvents; }
// }
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credentials")
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String credentialId;
    private String issuer;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    private String status;
    private String metadataJson;

    // Constructors
    public Credential() {}

    public Credential(Long employeeId, String credentialId, String issuer, LocalDateTime issuedAt, LocalDateTime expiresAt, String status, String metadataJson) {
        this.employeeId = employeeId;
        this.credentialId = credentialId;
        this.issuer = issuer;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.status = status;
        this.metadataJson = metadataJson;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getCredentialId() { return credentialId; }
    public void setCredentialId(String credentialId) { this.credentialId = credentialId; }

    public String getIssuer() { return issuer; }
    public void setIssuer(String issuer) { this.issuer = issuer; }

    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMetadataJson() { return metadataJson; }
    public void setMetadataJson(String metadataJson) { this.metadataJson = metadataJson; }
}