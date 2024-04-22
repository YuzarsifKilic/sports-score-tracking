package com.yuzarsif.api.dto;

import com.yuzarsif.api.model.SportType;

public record CreateFavoriteMatchRequest(
        Long userId,
        Long matchId,
        SportType sportType
) {
}
