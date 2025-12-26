package com.example.demo.service.impl;

import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Credential;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.CredentialRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.CredentialVerificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CredentialVerificationServiceImpl implements CredentialVerificationService {

    private final CredentialRepository credentialRepo;
    private final EmployeeProfileRepository employeeRepo;

    public CredentialVerificationServiceImpl(
            CredentialRepository credentialRepo,
            EmployeeProfileRepository employeeRepo) {
        this.credentialRepo = credentialRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Credential registerCredential(Credential credential) {

        EmployeeProfile employee = employeeRepo.findById(
                credential.getEmployee().getId()
        ).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        credential.setStatus("PENDING");
        credential.setEmployee(employee);
        return credentialRepo.save(credential);
    }

    @Override
    public CredentialStatusDto verifyCredential(String credentialId) {

        Credential credential = credentialRepo.findByCredentialId(credentialId)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        credential.setStatus("VERIFIED");
        credentialRepo.save(credential);

        return new CredentialStatusDto(
                credentialId,
                "VERIFIED",
                LocalDateTime.now(),
                "Verification successful"
        );
    }

    @Override
    public List<Credential> getCredentialsForEmployee(Long employeeId) {
        return credentialRepo.findByEmployeeId(employeeId);
    }
}
