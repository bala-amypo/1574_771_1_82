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

    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserAccountService userAccountService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {
        UserAccount registered = userAccountService.registerUser(user);
        return ResponseEntity.ok(registered);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        UserAccount user = userAccountService.findByEmail(request.getEmail());
        
        if (passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail(), user.getRoles());
            AuthResponse response = new AuthResponse(token, user.getId(), user.getEmail(), user.getRoles());
            return ResponseEntity.ok(response);
        }
        
        return ResponseEntity.badRequest().build();
    }
}





// package com.example.demo.controller;

// import com.example.demo.dto.AuthRequest;
// import com.example.demo.dto.AuthResponse;
// import com.example.demo.model.UserAccount;
// import com.example.demo.security.JwtTokenProvider;
// import com.example.demo.service.UserAccountService;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.*;

// import java.util.Set;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private final AuthenticationManager authenticationManager;
//     private final JwtTokenProvider jwtTokenProvider;
//     private final UserAccountService userService;
//     private final PasswordEncoder passwordEncoder;

//     public AuthController(AuthenticationManager authenticationManager,
//                           JwtTokenProvider jwtTokenProvider,
//                           UserAccountService userService,
//                           PasswordEncoder passwordEncoder) {
//         this.authenticationManager = authenticationManager;
//         this.jwtTokenProvider = jwtTokenProvider;
//         this.userService = userService;
//         this.passwordEncoder = passwordEncoder;
//     }

//     @PostMapping("/register")
//     public ResponseEntity<UserAccount> register(@RequestBody AuthRequest request) {
//         UserAccount user = new UserAccount();
//         user.setEmail(request.getEmail());
//         user.setPasswordHash(request.getPassword());
//         user.setRoles(Set.of("USER"));
//         UserAccount saved = userService.registerUser(user);
//         return ResponseEntity.ok(saved);
//     }

//     @PostMapping("/login")
//     public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
//         Authentication authentication = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//         String token = jwtTokenProvider.generateToken(1L, userDetails.getUsername(), "USER"); // Simplified
//         AuthResponse response = new AuthResponse(token, 1L, userDetails.getUsername(), "USER");
//         return ResponseEntity.ok(response);
//     }
// }