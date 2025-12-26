package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
}
