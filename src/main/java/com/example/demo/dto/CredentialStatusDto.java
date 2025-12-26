package com.example.demo.dto;

import java.time.LocalDateTime;

public class CredentialStatusDto {

    private String credentialId;
    private String status;
    private LocalDateTime verifiedAt;
    private String details;

    public CredentialStatusDto() {}

    public CredentialStatusDto(String credentialId, String status,
                               LocalDateTime verifiedAt, String details) {
        this.credentialId = credentialId;
        this.status = status;
        this.verifiedAt = verifiedAt;
        this.details = details;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getVerifiedAt() {
        return verifiedAt;
    }

    public String getDetails() {
        return details;
    }
}
