package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.UserAccount;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService userService;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwt;

    public AuthController(UserAccountService userService, PasswordEncoder encoder, JwtTokenProvider jwt) {
        this.userService = userService;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        user.setPasswordHash(encoder.encode(user.getPasswordHash()));
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        UserAccount user = userService.findByEmail(req.getEmail());
        if (!encoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new IllegalStateException("Invalid credentials");
        }
        String token = jwt.generateToken(user.getId(), user.getEmail(), user.getRole().iterator().next());
        return new AuthResponse(token, user.getId(), user.getEmail(), user.getRole());
    }
}
