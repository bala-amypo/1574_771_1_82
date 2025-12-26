package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credential_verification_events")
public class CredentialVerificationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime verifiedAt;

    private String result = "FAILED";

    @Column(columnDefinition = "TEXT")
    private String details;

    @ManyToOne
    @JoinColumn(name = "credential_id")
    private Credential credential;

    public CredentialVerificationEvent() {}

    @PrePersist
    public void onVerify() {
        this.verifiedAt = LocalDateTime.now();
        if (this.result == null) {
            this.result = "FAILED";
        }
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public String getResult() {
        return result;
    }

    public boolean isSuccess() {
        return "SUCCESS".equalsIgnoreCase(result);
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    /* 🔥 AMYPO TEST-CRITICAL METHOD */
    public Long getCredentialId() {
        return credential != null ? credential.getId() : null;
    }
}
