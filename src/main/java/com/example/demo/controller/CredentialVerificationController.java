package com.example.demo.controller;

import com.example.demo.dto.CredentialRequestDto;
import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.model.Credential;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.CredentialVerificationService;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credentials")
public class CredentialVerificationController {

    private final CredentialVerificationService credentialService;
    private final EmployeeProfileService employeeService;

    public CredentialVerificationController(
            CredentialVerificationService credentialService,
            EmployeeProfileService employeeService) {
        this.credentialService = credentialService;
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Credential> register(
            @RequestBody CredentialRequestDto dto) {

        EmployeeProfile employee =
                employeeService.getEmployeeById(dto.getEmployeeId());

        Credential credential = new Credential();
        credential.setCredentialId(dto.getCredentialId());
        credential.setIssuer(dto.getIssuer());
        credential.setIssuedAt(dto.getIssuedAt());
        credential.setExpiresAt(dto.getExpiresAt());
        credential.setMetadataJson(dto.getMetadataJson());
        credential.setEmployee(employee);

        return new ResponseEntity<>(
                credentialService.registerCredential(credential),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/verify/{credentialId}")
    public ResponseEntity<CredentialStatusDto> verify(
            @PathVariable String credentialId) {
        return ResponseEntity.ok(
                credentialService.verifyCredential(credentialId)
        );
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Credential>> byEmployee(
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(
                credentialService.getCredentialsForEmployee(employeeId)
        );
    }
}
