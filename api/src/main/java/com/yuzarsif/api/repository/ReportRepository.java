package com.yuzarsif.api.repository;

import com.yuzarsif.api.model.Report;
import com.yuzarsif.api.model.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByReportStatus(ReportStatus reportStatus);
}
