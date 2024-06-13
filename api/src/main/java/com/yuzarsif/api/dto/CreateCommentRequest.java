package com.yuzarsif.api.dto;

import com.yuzarsif.api.model.SportType;

public record CreateCommentRequest(
        Long footballFanId,
        Long matchId,
        String comment,
        SportType sportType
) {
}
