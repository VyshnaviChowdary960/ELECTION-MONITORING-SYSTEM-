package com.election.monitoring.service;

import com.election.monitoring.dto.AuthResponse;
import com.election.monitoring.dto.SignInRequest;
import com.election.monitoring.dto.SignUpRequest;
import com.election.monitoring.entity.User;
import com.election.monitoring.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse signUp(SignUpRequest request) {
        System.out.println("Signup request received:");
        System.out.println("Name: " + request.getName());
        System.out.println("Email: " + request.getEmail());
        System.out.println("Password: " + request.getPassword());
        System.out.println("Role: " + request.getRole());

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User(
            request.getName(),
            request.getEmail(),
            request.getPassword(),
            "citizen"
        );

        userRepository.save(user);

        return new AuthResponse("Signup successful", user.getRole(), user.getEmail());
    }

    public AuthResponse signIn(SignInRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!request.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new AuthResponse("Login successful", user.getRole(), user.getEmail());
    }

    public String deleteAccount(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
        return "Account deleted successfully";
    }
}