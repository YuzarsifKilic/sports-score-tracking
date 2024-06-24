package com.yuzarsif.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long matchId;
    @ManyToOne
    @JoinColumn(name = "sport_fan_id")
    private SportFan sportFan;
    private String content;
    private Integer likeCount;
    private SportType sportType;
    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private Set<Report> comments;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", sportFan=" + sportFan +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", sportType=" + sportType +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(matchId, comment.matchId) && Objects.equals(sportFan, comment.sportFan) && Objects.equals(content, comment.content) && Objects.equals(likeCount, comment.likeCount) && Objects.equals(createdDate, comment.createdDate) && Objects.equals(updatedDate, comment.updatedDate) && Objects.equals(sportType, comment.sportType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matchId, sportFan, content, likeCount, sportType, createdDate, updatedDate);
    }
}
