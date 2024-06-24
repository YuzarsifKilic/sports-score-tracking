package com.yuzarsif.api.dto;

public record CreateAdminRequest(
    String email,
    String password,
    String firstName,
    String lastName,
    String username
) {
}
