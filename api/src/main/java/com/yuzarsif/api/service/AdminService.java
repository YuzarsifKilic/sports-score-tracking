package com.yuzarsif.api.service;

import com.yuzarsif.api.dto.CreateAdminRequest;
import com.yuzarsif.api.model.Admin;
import com.yuzarsif.api.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, AuthService authService, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    public void createAdmin(CreateAdminRequest request) {
        authService.checkIfEmailExists(request.email());

        Admin admin = Admin
                .builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .username(request.username())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();

        adminRepository.save(admin);
    }


}
