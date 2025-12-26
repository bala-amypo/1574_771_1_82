package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository repo;
    private final PasswordEncoder encoder;

    public UserAccountServiceImpl(UserAccountRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public UserAccount registerUser(UserAccount user) {
        repo.findByEmail(user.getEmail()).ifPresent(u -> { throw new IllegalStateException("User exists"); });
        user.setPasswordHash(encoder.encode(user.getPasswordHash()));
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(java.util.Set.of("ADMIN"));
        }
        return repo.save(user);
    }

    @Override
    public UserAccount findByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public UserAccount findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}