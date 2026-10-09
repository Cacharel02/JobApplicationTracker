package com.benjamin.JobApplicationTracker.service;

import com.benjamin.JobApplicationTracker.dto.AuthResponse;
import com.benjamin.JobApplicationTracker.dto.LoginRequest;
import com.benjamin.JobApplicationTracker.dto.RegisterRequest;
import com.benjamin.JobApplicationTracker.entity.Role;
import com.benjamin.JobApplicationTracker.entity.User;
import com.benjamin.JobApplicationTracker.entity.UserStatus;
import com.benjamin.JobApplicationTracker.exception.UserAlreadyExistsException;
import com.benjamin.JobApplicationTracker.repository.UserRepository;
import com.benjamin.JobApplicationTracker.security.JwtService;
import com.benjamin.JobApplicationTracker.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public void register(RegisterRequest registerRequest) {
        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(registerRequest.getEmail());
        }

        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.SIMPLE_USER);
        user.setUserStatus(UserStatus.PENDING);
        
        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String token = jwtService.generateToken(securityUser);
        
        return new AuthResponse(token);
    }
}
