package com.yuzarsif.api.service;

import com.yuzarsif.api.dto.AuthResponseDto;
import com.yuzarsif.api.dto.LoginRequest;
import com.yuzarsif.api.model.BaseUser;
import com.yuzarsif.api.repository.BaseUserRepository;
import com.yuzarsif.api.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final BaseUserRepository baseUserRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(BaseUserRepository baseUserRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.baseUserRepository = baseUserRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponseDto login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(request.email());
            BaseUser baseUserByUsername = baseUserRepository.findByEmail(request.email()).get();
            AuthResponseDto authResponseDto = AuthResponseDto
                    .builder()
                    .role(baseUserByUsername.getRole().name())
                    .token(token)
                    .id(baseUserByUsername.getId())
                    .build();
            return authResponseDto;
        }

        throw new EntityNotFoundException("User not found");
    }
}
