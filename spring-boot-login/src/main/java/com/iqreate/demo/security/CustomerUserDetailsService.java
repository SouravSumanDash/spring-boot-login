package com.iqreate.demo.security;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.iqreate.demo.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        // if contains '@' → treat as email, else username
        return (input.contains("@") 
                ? userRepository.findByEmail(input) 
                : userRepository.findByUsername(input))
            .map(user -> new org.springframework.security.core.userdetails.User(
                    user.getUsername(),   // Spring Security username field
                    user.getPassword(),   // bcrypt hashed password
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
            ))
            .orElseThrow(() -> new UsernameNotFoundException("User not found with: " + input));
    }
}
