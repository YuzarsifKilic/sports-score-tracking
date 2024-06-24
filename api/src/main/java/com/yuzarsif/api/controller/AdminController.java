package com.yuzarsif.api.controller;

import com.yuzarsif.api.dto.CreateAdminRequest;
import com.yuzarsif.api.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createAdmin(@RequestBody CreateAdminRequest request) {
        adminService.createAdmin(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
