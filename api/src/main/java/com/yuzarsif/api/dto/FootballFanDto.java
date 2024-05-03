package com.yuzarsif.api.dto;

import com.yuzarsif.api.model.FootballFan;

public record FootballFanDto(
    Long id,
    String email,
    String firstName,
    String lastName
) {

    public static FootballFanDto convert(FootballFan footballFan) {
        return new FootballFanDto(
            footballFan.getId(),
            footballFan.getEmail(),
            footballFan.getFirstName(),
            footballFan.getLastName());
    }
}
