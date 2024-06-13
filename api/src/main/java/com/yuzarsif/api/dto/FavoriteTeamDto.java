package com.yuzarsif.api.dto;

import com.yuzarsif.api.model.FavoriteTeam;
import com.yuzarsif.api.model.SportType;

public record FavoriteTeamDto(
        Long id,
        Long teamId,
        SportType sportType
) {

    public static FavoriteTeamDto convert(FavoriteTeam from) {
        return new FavoriteTeamDto(from.getId(), from.getTeamId(), from.getSportType());
    }
}
