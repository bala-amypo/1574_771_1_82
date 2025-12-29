// package com.example.demo.controller;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.web.bind.annotation.*;

// import java.nio.charset.StandardCharsets;
// import java.security.Key;
// import java.util.Date;
// import java.util.Map;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     private static final String SECRET =
//             "mysecretkeymysecretkeymysecretkey123";

//     private final Key key =
//             Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

//     @PostMapping("/login")
//     public Map<String, String> login(
//             @RequestParam String username,
//             @RequestParam String password
//     ) {

//         // SIMPLE AUTH CHECK
//         if (!username.equals("admin") || !password.equals("password")) {
//             throw new RuntimeException("Invalid credentials");
//         }

//         String token = Jwts.builder()
//                 .setSubject(username)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
//                 .signWith(key, SignatureAlgorithm.HS256)
//                 .compact();

//         return Map.of("token", token);
//     }
// }

package com.example.demo.controller;

import com.example.demo.model.UserAccount;
import com.example.demo.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService service;

    public AuthController(UserAccountService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserAccount user) {
        String token = service.login(user);
        return Map.of("token", token);
    }
}