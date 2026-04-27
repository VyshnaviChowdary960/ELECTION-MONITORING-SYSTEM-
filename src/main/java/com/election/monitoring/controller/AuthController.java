package com.election.monitoring.controller;

import com.election.monitoring.dto.AuthResponse;
import com.election.monitoring.dto.SignInRequest;
import com.election.monitoring.dto.SignUpRequest;
import com.election.monitoring.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public AuthResponse signUp(@RequestBody SignUpRequest request) {
        return authService.signUp(request);
    }

    @PostMapping("/signin")
    public AuthResponse signIn(@RequestBody SignInRequest request) {
        return authService.signIn(request);
    }

    @DeleteMapping("/delete/{email}")
    public String deleteAccount(@PathVariable String email) {
        return authService.deleteAccount(email);
    }
}