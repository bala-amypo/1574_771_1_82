// package com.example.demo.service;

// import com.example.demo.model.UserAccount;

// public interface UserAccountService {
//     UserAccount registerUser(UserAccount user);
//     UserAccount findByEmail(String email);
//     UserAccount findById(Long id);
// }
package com.example.demo.service;

import com.example.demo.model.UserAccount;

import java.util.Optional;

public interface UserAccountService {
    UserAccount registerUser(UserAccount user);
    UserAccount findByEmail(String email);
    UserAccount findById(Long id);
}