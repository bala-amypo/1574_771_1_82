package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Credential;
import com.example.demo.repository.CredentialRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.CredentialVerificationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CredentialVerificationServiceImpl implements CredentialVerificationService {

    private final CredentialRepository credRepo;
    private final EmployeeProfileRepository empRepo;

    public CredentialVerificationServiceImpl(CredentialRepository credRepo,
                                             EmployeeProfileRepository empRepo) {
        this.credRepo = credRepo;
        this.empRepo = empRepo;
    }

    public Credential registerCredential(CredentialRequestDto dto) {

        empRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Credential c = new Credential();
        c.setEmployeeId(dto.getEmployeeId());
        c.setCredentialId(dto.getCredentialId());
        c.setIssuer(dto.getIssuer());
        c.setIssuedAt(dto.getIssuedAt());
        c.setExpiresAt(dto.getExpiresAt());
        c.setMetadataJson(dto.getMetadataJson());
        c.setStatus("PENDING");

        return credRepo.save(c);
    }

    public CredentialStatusDto verifyCredential(String credentialId) {

        Credential c = credRepo.findByCredentialId(credentialId)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        if (c.getExpiresAt() != null && c.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new IllegalStateException("Credential expired");

        c.setStatus("VERIFIED");
        credRepo.save(c);

        return new CredentialStatusDto(
                credentialId,
                c.getStatus(),
                LocalDateTime.now(),
                "Verification successful"
        );
    }

    public List<Credential> getCredentialsForEmployee(Long employeeId) {
        return credRepo.findByEmployeeId(employeeId);
    }
}
