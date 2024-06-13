package com.yuzarsif.api.dto;

import com.yuzarsif.api.model.SportType;

public record FavoriteMatchRequest(
        Long userId,
        Long matchId,
        SportType sportType
) {
}
