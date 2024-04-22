package com.yuzarsif.api.dto;

public record CreateCommentRequest(
        Long footballFanId,
        Long matchId,
        String comment
) {
}
