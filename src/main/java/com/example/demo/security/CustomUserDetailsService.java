// package com.example.demo.security;

// import com.example.demo.model.UserAccount;
// import com.example.demo.repository.UserAccountRepository;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import java.util.Collection;
// import java.util.stream.Collectors;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     private final UserAccountRepository userAccountRepository;

//     public CustomUserDetailsService(UserAccountRepository userAccountRepository) {
//         this.userAccountRepository = userAccountRepository;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         UserAccount userAccount = userAccountRepository.findByEmail(email)
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//         Collection<GrantedAuthority> authorities = userAccount.getRoles().stream()
//                 .map(SimpleGrantedAuthority::new)
//                 .collect(Collectors.toList());

//         return new User(userAccount.getEmail(), userAccount.getPasswordHash(), authorities);
//     }
// }



package com.example.demo.security;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public CustomUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(user.getEmail(), user.getPasswordHash(),
                user.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
    }
}