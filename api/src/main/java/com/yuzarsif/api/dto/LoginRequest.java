package com.yuzarsif.api.dto;

public record LoginRequest(
        String email,
        String password
) {
}
