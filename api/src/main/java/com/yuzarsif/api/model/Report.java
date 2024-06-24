package com.yuzarsif.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "sport_fan_id")
    private SportFan sportFan;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;
    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(description, report.description) && Objects.equals(sportFan, report.sportFan) && Objects.equals(comment, report.comment) && Objects.equals(createdDate, report.createdDate) && Objects.equals(reportStatus, report.reportStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, sportFan, comment, createdDate, reportStatus);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", sportFan=" + sportFan +
                ", comment=" + comment +
                ", reportStatus=" + reportStatus +
                ", createdDate=" + createdDate +
                '}';
    }
}
