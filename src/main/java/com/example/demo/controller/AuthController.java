package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.UserAccount;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService userService;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwt;

    public AuthController(UserAccountService userService,
                          PasswordEncoder encoder,
                          JwtTokenProvider jwt) {
        this.userService = userService;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {
        user.setPasswordHash(encoder.encode(user.getPasswordHash()));
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        UserAccount user = userService.findByEmail(req.getEmail());
        String token = jwt.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRoles().iterator().next()
        );
        return ResponseEntity.ok(
                new AuthResponse(token, user.getId(), user.getEmail(), user.getRoles())
        );
    }
}
