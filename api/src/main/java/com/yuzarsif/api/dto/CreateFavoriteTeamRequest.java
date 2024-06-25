package com.yuzarsif.api.dto;

import com.yuzarsif.api.model.SportType;

public record CreateFavoriteTeamRequest(
        Long teamId,
        SportType sportType
) {
}
