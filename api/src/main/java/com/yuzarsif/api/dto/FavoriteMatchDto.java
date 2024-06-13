package com.yuzarsif.api.dto;

import com.yuzarsif.api.model.FavoriteMatch;
import com.yuzarsif.api.model.SportType;

public record FavoriteMatchDto(
    Long id,
    Long matchId,
    SportType sportType
) {
    public static FavoriteMatchDto convert(FavoriteMatch from) {
        return new FavoriteMatchDto(from.getId(), from.getMatchId(), from.getSportType());
    }
}
