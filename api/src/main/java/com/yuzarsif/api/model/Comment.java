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
    @JoinColumn(name = "football_fan_id")
    private FootballFan footballFan;
    private String content;
    private Integer likeCount;
    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", footballFan=" + footballFan +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(matchId, comment.matchId) && Objects.equals(footballFan, comment.footballFan) && Objects.equals(content, comment.content) && Objects.equals(likeCount, comment.likeCount) && Objects.equals(createdDate, comment.createdDate) && Objects.equals(updatedDate, comment.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matchId, footballFan, content, likeCount, createdDate, updatedDate);
    }
}
