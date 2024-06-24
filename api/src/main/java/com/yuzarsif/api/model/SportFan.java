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
public class SportFan extends BaseUser {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "sport_fan_favorite_team",
            joinColumns = @JoinColumn(name = "sport_fan_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_team_id"))
    private Set<FavoriteTeam> favoriteTeams;
    @OneToMany(mappedBy = "sportFan", cascade = CascadeType.REMOVE)
    private Set<Comment> comments;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "sport_fan_favorite_match",
            joinColumns = @JoinColumn(name = "sport_fan_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_match_id"))
    private Set<FavoriteMatch> favoriteMatches;
    @OneToMany(mappedBy = "sportFan", cascade = CascadeType.REMOVE)
    private Set<Report> reports;

    @Override
    public String toString() {
        return "SportFan{" +
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
        SportFan that = (SportFan) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, phoneNumber);
    }
}
