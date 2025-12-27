package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user_accounts")
public class UserAccount {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    private String passwordHash;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    private LocalDateTime createdAt = LocalDateTime.now();

    public UserAccount() {}

    public void setRoles(Set<String> roles) { this.roles = roles; }
    public Set<String> getRoles() { return roles; }
}
