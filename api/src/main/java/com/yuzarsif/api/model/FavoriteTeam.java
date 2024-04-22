package com.yuzarsif.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FavoriteTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long teamId;
    @Enumerated(EnumType.STRING)
    private SportType sportType;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "football_fan_favorite_team",
            joinColumns = @JoinColumn(name = "favorite_team_id"),
            inverseJoinColumns = @JoinColumn(name = "football_fan_id"))
    private Set<FootballFan> footballFans;

    @Override
    public String toString() {
        return "FavoriteTeam{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", sportType=" + sportType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteTeam that = (FavoriteTeam) o;
        return Objects.equals(id, that.id) && Objects.equals(teamId, that.teamId) && Objects.equals(sportType, that.sportType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamId, sportType);
    }
}
