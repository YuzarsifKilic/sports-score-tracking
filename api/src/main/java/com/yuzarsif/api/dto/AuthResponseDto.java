package com.yuzarsif.api.dto;

import lombok.Builder;

@Builder
public record AuthResponseDto(
        String token,
        String role,
        Long id
) {
}
