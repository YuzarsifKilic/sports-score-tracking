package com.yuzarsif.api.dto;

import com.yuzarsif.api.model.SportFan;

public record FootballFanDto(
    Long id,
    String email,
    String firstName,
    String lastName
) {

    public static FootballFanDto convert(SportFan sportFan) {
        return new FootballFanDto(
            sportFan.getId(),
            sportFan.getEmail(),
            sportFan.getFirstName(),
            sportFan.getLastName());
    }
}
