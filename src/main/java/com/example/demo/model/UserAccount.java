// package com.example.demo.model;

// import javax.persistence.*;
// import java.time.LocalDateTime;
// import java.util.HashSet;
// import java.util.Set;

// @Entity
// @Table(name = "user_accounts")
// public class UserAccount {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String username;

//     @Column(unique = true, nullable = false)
//     private String email;

//     @Column(name = "password_hash", nullable = false)
//     private String passwordHash;

//     @ElementCollection(fetch = FetchType.EAGER)
//     @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
//     @Column(name = "roles")
//     private Set<String> roles = new HashSet<>();

//     @Column(name = "created_at")
//     private LocalDateTime createdAt;

//     public UserAccount() {}

//     public UserAccount(String username, String email, String passwordHash) {
//         this.username = username;
//         this.email = email;
//         this.passwordHash = passwordHash;
//         this.roles = new HashSet<>();
//     }

//     @PrePersist
//     protected void onCreate() {
//         createdAt = LocalDateTime.now();
//     }

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public String getUsername() { return username; }
//     public void setUsername(String username) { this.username = username; }

//     public String getEmail() { return email; }
//     public void setEmail(String email) { this.email = email; }

//     public String getPasswordHash() { return passwordHash; }
//     public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

//     public Set<String> getRoles() { return roles; }
//     public void setRoles(Set<String> roles) { this.roles = roles; }

//     public LocalDateTime getCreatedAt() { return createdAt; }
//     public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
// }
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user_accounts")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @Column(unique = true)
    private String email;
    private String passwordHash;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    // Constructors
    public UserAccount() {}

    public UserAccount(String username, String email, String passwordHash, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.roles = roles;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}