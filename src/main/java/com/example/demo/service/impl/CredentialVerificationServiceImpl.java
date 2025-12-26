package com.example.demo.service.impl;

import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.model.Credential;
import com.example.demo.model.CredentialVerificationEvent;
import com.example.demo.repository.CredentialRepository;
import com.example.demo.repository.CredentialVerificationEventRepository;
import com.example.demo.service.CredentialVerificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialVerificationServiceImpl implements CredentialVerificationService {

    private final CredentialRepository credentialRepo;
    private final CredentialVerificationEventRepository eventRepo;

    public CredentialVerificationServiceImpl(
            CredentialRepository credentialRepo,
            CredentialVerificationEventRepository eventRepo) {
        this.credentialRepo = credentialRepo;
        this.eventRepo = eventRepo;
    }

    @Override
    public Credential registerCredential(Credential credential) {
        credential.setStatus("PENDING");
        return credentialRepo.save(credential);
    }

    @Override
    public CredentialStatusDto verifyCredential(String credentialId) {

        Credential credential = credentialRepo.findByCredentialId(credentialId);
        if (credential == null) {
            throw new RuntimeException("Credential not found");
        }

        credential.setStatus("VERIFIED");
        credentialRepo.save(credential);

        CredentialVerificationEvent event = new CredentialVerificationEvent();
        event.setCredential(credential);
        event.setResult("SUCCESS");
        event.setDetails("Credential verified successfully");
        eventRepo.save(event);

        return new CredentialStatusDto(
                credential.getCredentialId(),
                credential.getStatus()
        );
    }

    @Override
    public List<Credential> getCredentialsForEmployee(Long employeeId) {
        return credentialRepo.findByEmployee_Id(employeeId);
    }
}
