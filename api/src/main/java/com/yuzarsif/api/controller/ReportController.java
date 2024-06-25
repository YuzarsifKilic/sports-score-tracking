package com.yuzarsif.api.controller;

import com.yuzarsif.api.dto.CreateReportRequest;
import com.yuzarsif.api.dto.ReportDto;
import com.yuzarsif.api.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createReport(@RequestBody CreateReportRequest request, Authentication authentication) {
        reportService.createReport(request, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ReportDto>> findAll() {
        return ResponseEntity.ok(reportService.findAll());
    }

    @GetMapping("/in-progress")
    public ResponseEntity<List<ReportDto>> findByInProgress() {
        return ResponseEntity.ok(reportService.findByInProgress());
    }
}
