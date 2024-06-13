package com.yuzarsif.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FavoriteMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long matchId;
    @Enumerated(EnumType.STRING)
    private SportType sportType;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "sport_fan_favorite_match",
            joinColumns = @JoinColumn(name = "favorite_match_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_fan_id"))
    private Set<SportFan> sportFans;

    @Override
    public String toString() {
        return "FavoriteMatch{" +
                "id=" + id +
                ", matchId=" + matchId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteMatch that = (FavoriteMatch) o;
        return Objects.equals(id, that.id) && Objects.equals(matchId, that.matchId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matchId);
    }
}
