package com.yuzarsif.api.dto;

public record CreateReportRequest(
        Long sportFanId,
        String description
) {
}
