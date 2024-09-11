package com.uade.tpo.courseCommerce.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uade.tpo.courseCommerce.controller.auth.AuthenticationRequest;
import com.uade.tpo.courseCommerce.controller.auth.AuthenticationResponse;
import com.uade.tpo.courseCommerce.controller.auth.RegisterRequest;
import com.uade.tpo.courseCommerce.controller.config.JwtService;
import com.uade.tpo.courseCommerce.entity.User;
import com.uade.tpo.courseCommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
                var user = User.builder()
                                .username(request.getUsername())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(request.getRole())
                                .build();

                repository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {

                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));
                
                                                
                var user = repository.findByEmail(request.getEmail()).orElseThrow();

                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }

        public Optional<User> getUserByUsername(String username) {
                return repository.findByUsername(username);
        }

}
