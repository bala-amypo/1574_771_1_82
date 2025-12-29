// package com.example.demo.service.impl;

// import com.example.demo.dto.CredentialStatusDto;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.Credential;
// import com.example.demo.model.CredentialVerificationEvent;
// import com.example.demo.model.EmployeeProfile;
// import com.example.demo.repository.CredentialRepository;
// import com.example.demo.repository.CredentialVerificationEventRepository;
// import com.example.demo.repository.EmployeeProfileRepository;
// import com.example.demo.service.CredentialVerificationService;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class CredentialVerificationServiceImpl implements CredentialVerificationService {

//     private final CredentialRepository credentialRepository;
//     private final CredentialVerificationEventRepository eventRepository;
//     private final EmployeeProfileRepository employeeRepository;

//     public CredentialVerificationServiceImpl(CredentialRepository credentialRepository,
//                                            CredentialVerificationEventRepository eventRepository,
//                                            EmployeeProfileRepository employeeRepository) {
//         this.credentialRepository = credentialRepository;
//         this.eventRepository = eventRepository;
//         this.employeeRepository = employeeRepository;
//     }

//     @Override
//     public Credential registerCredential(Credential credential) {
//         EmployeeProfile employee = employeeRepository.findById(credential.getEmployeeId())
//                 .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        
//         if (!employee.getActive()) {
//             throw new ResourceNotFoundException("Employee not found");
//         }

//         return credentialRepository.save(credential);
//     }

//     @Override
//     public CredentialStatusDto verifyCredential(String credentialId) {
//         Credential credential = credentialRepository.findByCredentialId(credentialId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

//         String result = "SUCCESS";
//         String details = "Credential verified successfully";
//         LocalDateTime now = LocalDateTime.now();

//         if (credential.getExpiresAt() != null && credential.getExpiresAt().isBefore(now)) {
//             credential.setStatus("EXPIRED");
//             result = "FAILURE";
//             details = "Credential expired";
//             throw new IllegalStateException("Credential expired");
//         }

//         if ("REVOKED".equals(credential.getStatus())) {
//             result = "FAILURE";
//             details = "Credential revoked";
//             throw new IllegalStateException("Credential revoked");
//         }

//         credential.setStatus("VERIFIED");
//         credentialRepository.save(credential);

//         CredentialVerificationEvent event = new CredentialVerificationEvent(credential.getId(), result, details);
//         eventRepository.save(event);

//         return new CredentialStatusDto(credentialId, credential.getStatus(), now, details);
//     }

//     @Override
//     public List<Credential> getCredentialsForEmployee(Long employeeId) {
//         return credentialRepository.findByEmployeeId(employeeId);
//     }
// }
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

    private final CredentialRepository credentialRepository;
    private final CredentialVerificationEventRepository eventRepository;
    private final EmployeeProfileRepository employeeRepository;

    public CredentialVerificationServiceImpl(CredentialRepository credentialRepository,
                                             CredentialVerificationEventRepository eventRepository,
                                             EmployeeProfileRepository employeeRepository) {
        this.credentialRepository = credentialRepository;
        this.eventRepository = eventRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Credential registerCredential(Credential credential) {
        EmployeeProfile employee = employeeRepository.findById(credential.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (!employee.getActive()) {
            throw new ResourceNotFoundException("Employee not found");
        }
        credential.setStatus("PENDING");
        return credentialRepository.save(credential);
    }

    @Override
    public CredentialStatusDto verifyCredential(String credentialId) {
        Credential credential = credentialRepository.findByCredentialId(credentialId)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        String status;
        String details = "";
        LocalDateTime verifiedAt = LocalDateTime.now();

        if ("EXPIRED".equals(credential.getStatus()) || (credential.getExpiresAt() != null && LocalDateTime.now().isAfter(credential.getExpiresAt()))) {
            status = "EXPIRED";
            details = "Credential expired";
        } else if ("REVOKED".equals(credential.getStatus())) {
            status = "REVOKED";
            details = "Credential revoked";
        } else {
            status = "VERIFIED";
            credential.setStatus("VERIFIED");
            credentialRepository.save(credential);
        }

        CredentialVerificationEvent event = new CredentialVerificationEvent();
        event.setCredentialId(credential.getId());
        event.setVerifiedAt(verifiedAt);
        event.setResult(status.equals("VERIFIED") ? "SUCCESS" : "FAILURE");
        event.setDetails(details);
        eventRepository.save(event);

        CredentialStatusDto dto = new CredentialStatusDto();
        dto.setCredentialId(credentialId);
        dto.setStatus(status);
        dto.setVerifiedAt(verifiedAt);
        dto.setDetails(details);
        return dto;
    }

    @Override
    public List<Credential> getCredentialsForEmployee(Long employeeId) {
        return credentialRepository.findByEmployeeId(employeeId);
    }
}