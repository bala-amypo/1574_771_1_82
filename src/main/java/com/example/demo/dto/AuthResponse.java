package com.example.demo.dto;

import java.util.Set;

public class AuthResponse {

    private String token;
    private Long userId;
    private String email;
    private Set<String> role;

    public AuthResponse(String token, Long userId, String email, Set<String> role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getRole() {
        return role;
    }
}
