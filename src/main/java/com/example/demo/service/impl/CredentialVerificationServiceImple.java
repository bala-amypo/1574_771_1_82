package com.example.demo.service.impl;

import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Credential;
import com.example.demo.model.CredentialVerificationEvent;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.CredentialRepository;
import com.example.demo.repository.CredentialVerificationEventRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.CredentialVerificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CredentialVerificationServiceImpl implements CredentialVerificationService {

    private final CredentialRepository credentialRepo;
    private final CredentialVerificationEventRepository eventRepo;
    private final EmployeeProfileRepository empRepo;

    public CredentialVerificationServiceImpl(CredentialRepository credentialRepo,
                                             CredentialVerificationEventRepository eventRepo,
                                             EmployeeProfileRepository empRepo) {
        this.credentialRepo = credentialRepo;
        this.eventRepo = eventRepo;
        this.empRepo = empRepo;
    }

    @Override
    public Credential registerCredential(Credential credential) {
        Long empId = credential.getEmployeeId();
        if (empId == null || empRepo.findById(empId).isEmpty()) {
            throw new ResourceNotFoundException("Employee not found");
        }
        var emp = empRepo.findById(empId).get();
        if (!Boolean.TRUE.equals(emp.getActive())) {
            throw new IllegalStateException("Employee not active");
        }
        return credentialRepo.save(credential);
    }

    @Override
    public CredentialStatusDto verifyCredential(String credentialId) {
        var credOpt = credentialRepo.findByCredentialId(credentialId);
        if (credOpt.isEmpty()) {
            throw new ResourceNotFoundException("Credential not found");
        }
        var cred = credOpt.get();
        if ("REVOKED".equalsIgnoreCase(cred.getStatus())) {
            throw new IllegalStateException("Credential revoked");
        }
        if (cred.getExpiresAt() != null && cred.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Credential expired");
        }
        cred.setStatus("VERIFIED");
        credentialRepo.save(cred);

        CredentialVerificationEvent ev = new CredentialVerificationEvent();
        ev.setCredentialId(cred.getId());
        ev.setVerifiedAt(LocalDateTime.now());
        ev.setResult("SUCCESS");
        ev.setDetails("Verified");
        eventRepo.save(ev);

        CredentialStatusDto dto = new CredentialStatusDto();
        dto.setCredentialId(credentialId);
        dto.setStatus("VERIFIED");
        dto.setVerifiedAt(ev.getVerifiedAt());
        dto.setDetails(ev.getDetails());
        return dto;
    }

    @Override
    public List<Credential> getCredentialsForEmployee(Long employeeId) {
        return credentialRepo.findByEmployeeId(employeeId);
    }

    @Override
    public Credential getCredentialByCredentialId(String credentialId) {
        return credentialRepo.findByCredentialId(credentialId).orElseThrow(() -> new ResourceNotFoundException("Credential not found"));
    }
}