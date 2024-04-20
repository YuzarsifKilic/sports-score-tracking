package com.yuzarsif.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class FootballFan extends BaseUser {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "football_fan_favorite_team",
            joinColumns = @JoinColumn(name = "football_fan_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_team_id"))
    private Set<FavoriteTeam> favoriteTeams;
    @OneToMany(mappedBy = "footballFan", cascade = CascadeType.REMOVE)
    private Set<Comment> comments;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "football_fan_favorite_match",
            joinColumns = @JoinColumn(name = "football_fan_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_match_id"))
    private Set<FavoriteMatch> favoriteMatches;

    @Override
    public String toString() {
        return "FootballFan{" +
                super.toString() +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", favoriteTeams=" + favoriteTeams +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FootballFan that = (FootballFan) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(favoriteTeams, that.favoriteTeams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, phoneNumber, favoriteTeams);
    }
}
