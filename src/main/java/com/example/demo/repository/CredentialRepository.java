package com.example.demo.repository;

import com.example.demo.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    List<Credential> findByEmployee_Id(Long employeeId);

    Credential findByCredentialId(String credentialId);
}
