package com.example.demo.controller;

import com.example.demo.dto.CredentialRequestDto;
import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.model.Credential;
import com.example.demo.service.CredentialVerificationService;
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
    public Credential register(@RequestBody Credential credential) {
        return credentialService.registerCredential(credential);
    }

    @PostMapping("/{credentialId}/verify")
    public CredentialStatusDto verify(@PathVariable String credentialId) {
        return credentialService.verifyCredential(credentialId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Credential> byEmployee(@PathVariable Long employeeId) {
        return credentialService.getCredentialsForEmployee(employeeId);
    }
}
