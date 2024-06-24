package com.yuzarsif.api.service;

import com.yuzarsif.api.dto.CreateReportRequest;
import com.yuzarsif.api.dto.ReportDto;
import com.yuzarsif.api.model.Report;
import com.yuzarsif.api.model.ReportStatus;
import com.yuzarsif.api.model.SportFan;
import com.yuzarsif.api.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final SportFanService sportFanService;

    public ReportService(ReportRepository reportRepository, SportFanService sportFanService) {
        this.reportRepository = reportRepository;
        this.sportFanService = sportFanService;
    }

    public List<ReportDto> findAll() {
        return reportRepository
                .findAll()
                .stream()
                .map(ReportDto::convert)
                .toList();
    }

    public List<ReportDto> findByInProgress() {
        return reportRepository
                .findByReportStatus(ReportStatus.IN_PROGRESS)
                .stream()
                .map(ReportDto::convert)
                .toList();
    }


    public void createReport(CreateReportRequest request) {
        SportFan sportFanById = sportFanService.findById(request.sportFanId());

        Report report = Report
                .builder()
                .description(request.description())
                .sportFan(sportFanById)
                .build();

        reportRepository.save(report);
    }


}
