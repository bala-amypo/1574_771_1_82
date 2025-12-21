package com.example.demo.service.impl;

import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Credential;
import com.example.demo.repository.CredentialRepository;
import com.example.demo.service.CredentialVerificationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CredentialVerificationServiceImpl implements CredentialVerificationService {

    private final CredentialRepository repo;

    public CredentialVerificationServiceImpl(CredentialRepository repo) {
        this.repo = repo;
    }

    public Credential registerCredential(Credential credential) {
        return repo.save(credential);
    }

    public CredentialStatusDto verifyCredential(String credentialId) {
        Credential c = repo.findByCredentialId(credentialId)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        if ("REVOKED".equals(c.getStatus()))
            throw new IllegalStateException("Credential revoked");

        return new CredentialStatusDto(
                credentialId,
                "VERIFIED",
                LocalDateTime.now(),
                "Verification successful"
        );
    }

    public List<Credential> getCredentialsForEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }

    public Credential getCredentialById(String credentialId) {
        return repo.findByCredentialId(credentialId)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));
    }
}
