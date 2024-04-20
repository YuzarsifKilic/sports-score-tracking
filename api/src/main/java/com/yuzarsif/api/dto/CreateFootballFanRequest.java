package com.yuzarsif.api.dto;

public record CreateFootballFanRequest(
        String email,
        String password,
        String firstName,
        String lastName,
        String phoneNumber
) {
}
