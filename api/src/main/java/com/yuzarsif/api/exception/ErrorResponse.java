package com.yuzarsif.api.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(String message,
                            LocalDateTime timestamp) {  }
