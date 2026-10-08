package com.benjamin.JobApplicationTracker.controller;

import com.benjamin.JobApplicationTracker.dto.AuthResponse;
import com.benjamin.JobApplicationTracker.dto.LoginRequest;
import com.benjamin.JobApplicationTracker.dto.RegisterRequest;
import com.benjamin.JobApplicationTracker.dto.ResponseDto;
import com.benjamin.JobApplicationTracker.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<ResponseDto> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "User registered successfully"));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.login(loginRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authResponse);
    }
}

