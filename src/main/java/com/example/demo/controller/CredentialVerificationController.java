package com.example.demo.controller;

import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.model.Credential;
import com.example.demo.service.CredentialVerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credentials")
public class CredentialVerificationController {

    private final CredentialVerificationService credentialService;

    public CredentialVerificationController(CredentialVerificationService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping
    public ResponseEntity<Credential> register(@RequestBody Credential credential) {
        Credential saved = credentialService.registerCredential(credential);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/{credentialId}/verify")
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
