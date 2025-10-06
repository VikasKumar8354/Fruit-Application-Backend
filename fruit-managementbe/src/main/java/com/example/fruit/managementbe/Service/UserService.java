package com.example.fruit.managementbe.Service;

import com.example.fruit.managementbe.DTOs.LoginRequest;
import com.example.fruit.managementbe.DTOs.SignupRequest;
import com.example.fruit.managementbe.JwtUtil.JwtUtil;
import com.example.fruit.managementbe.Model.User;
import com.example.fruit.managementbe.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    PasswordEncoder passwordEncoder;
     JwtUtil jwtUtil;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String signup(SignupRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            return "Email already registered!";
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepo.save(user);
        return "Signup successful!";
    }

    public String login(LoginRequest request) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials!");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}
