package com.iqreate.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iqreate.demo.model.User;
import com.iqreate.demo.repository.UserRepository;

@Service
public class UserService {
	
	public class PasswordEncoderTest {
	    public static void main(String[] args) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        String rawPassword = "password123"; // change this to your desired password
	        String encoded = encoder.encode(rawPassword);
	        System.out.println("Encoded password: " + encoded);
	    }
	}

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}

