package com.yuzarsif.api.dto;

import com.yuzarsif.api.model.Report;
import com.yuzarsif.api.model.ReportStatus;

public record ReportDto(
    Long id,
    String description,
    FootballFanDto sportFan,
    CommentDto comment,
    ReportStatus reportStatus
) {

    public static ReportDto convert(Report report) {
        return new ReportDto(
            report.getId(),
            report.getDescription(),
            FootballFanDto.convert(report.getSportFan()),
            CommentDto.convert(report.getComment()),
            report.getReportStatus());
    }
}
