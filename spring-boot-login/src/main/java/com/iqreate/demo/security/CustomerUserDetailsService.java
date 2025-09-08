package com.iqreate.demo.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.iqreate.demo.model.User;
import com.iqreate.demo.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	User user = userRepository.findByEmail(email)  
    			.orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));
    			// if contains '@' → treat as email
        return org.springframework.security.core.userdetails.User
        		.withUsername(user.getEmail())
        		.password(user.getPassword())
        		.authorities("ROLE_USER")
        		.build();

                      
            
    }
}
