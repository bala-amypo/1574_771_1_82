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
    private String result;
    private String details;

    @ManyToOne
    private Credential credential;

    @PrePersist
    public void onVerify() {
        this.verifiedAt = LocalDateTime.now();
    }

    public CredentialVerificationEvent() {}

    public Long getId() { return id; }

    public LocalDateTime getVerifiedAt() { return verifiedAt; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
