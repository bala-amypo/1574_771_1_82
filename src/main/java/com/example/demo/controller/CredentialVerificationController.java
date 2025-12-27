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

    private final CredentialVerificationService service;

    public CredentialVerificationController(CredentialVerificationService service) {
        this.service = service;
    }

    @PostMapping
    public Credential register(@RequestBody CredentialRequestDto dto) {
        return service.registerCredential(dto);
    }

    @PostMapping("/{credentialId}/verify")
    public CredentialStatusDto verify(@PathVariable String credentialId) {
        return service.verifyCredential(credentialId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Credential> byEmployee(@PathVariable Long employeeId) {
        return service.getCredentialsForEmployee(employeeId);
    }
}
