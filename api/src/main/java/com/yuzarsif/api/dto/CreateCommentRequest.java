package com.yuzarsif.api.dto;

import com.yuzarsif.api.model.SportType;

public record CreateCommentRequest(
        Long matchId,
        String comment,
        SportType sportType
) {
}
