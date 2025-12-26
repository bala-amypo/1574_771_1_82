package com.example.demo.controller;

import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.model.Credential;
import com.example.demo.service.CredentialVerificationService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Credential> register(@RequestBody Credential req) {
        return ResponseEntity.ok(service.registerCredential(req));
    }

    @PostMapping("/{credentialId}/verify")
    public ResponseEntity<CredentialStatusDto> verify(@PathVariable String credentialId) {
        return ResponseEntity.ok(service.verifyCredential(credentialId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Credential>> byEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(service.getCredentialsForEmployee(employeeId));
    }

    @GetMapping("/{credentialId}")
    public ResponseEntity<Credential> get(@PathVariable String credentialId) {
        return ResponseEntity.ok(service.getCredentialByCredentialId(credentialId));
    }
}