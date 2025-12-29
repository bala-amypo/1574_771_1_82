// package com.example.demo.model;

// import javax.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "credential_verification_events")
// public class CredentialVerificationEvent {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name = "credential_id", nullable = false)
//     private Long credentialId;

//     @Column(name = "verified_at")
//     private LocalDateTime verifiedAt;

//     private String result;
//     private String details;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "credential_id", insertable = false, updatable = false)
//     private Credential credential;

//     public CredentialVerificationEvent() {}

//     public CredentialVerificationEvent(Long credentialId, String result, String details) {
//         this.credentialId = credentialId;
//         this.result = result;
//         this.details = details;
//         this.verifiedAt = LocalDateTime.now();
//     }

//     @PrePersist
//     protected void onCreate() {
//         if (verifiedAt == null) {
//             verifiedAt = LocalDateTime.now();
//         }
//     }

//     // Getters and Setters
//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public Long getCredentialId() { return credentialId; }
//     public void setCredentialId(Long credentialId) { this.credentialId = credentialId; }

//     public LocalDateTime getVerifiedAt() { return verifiedAt; }
//     public void setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; }

//     public String getResult() { return result; }
//     public void setResult(String result) { this.result = result; }

//     public String getDetails() { return details; }
//     public void setDetails(String details) { this.details = details; }

//     public Credential getCredential() { return credential; }
//     public void setCredential(Credential credential) { this.credential = credential; }
// }
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credential_verification_events")
public class CredentialVerificationEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long credentialId;
    private LocalDateTime verifiedAt;
    private String result;
    private String details;

    // Constructors
    public CredentialVerificationEvent() {}

    public CredentialVerificationEvent(Long credentialId, LocalDateTime verifiedAt, String result, String details) {
        this.credentialId = credentialId;
        this.verifiedAt = verifiedAt;
        this.result = result;
        this.details = details;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCredentialId() { return credentialId; }
    public void setCredentialId(Long credentialId) { this.credentialId = credentialId; }

    public LocalDateTime getVerifiedAt() { return verifiedAt; }
    public void setVerifiedAt(LocalDateTime verifiedAt) { this.verifiedAt = verifiedAt; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}