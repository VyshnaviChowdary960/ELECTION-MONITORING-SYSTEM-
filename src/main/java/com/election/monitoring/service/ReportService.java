package com.election.monitoring.service;

import com.election.monitoring.entity.Report;
import com.election.monitoring.repository.ReportRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report saveReport(Report report) {
        if (report.getStatus() == null || report.getStatus().isBlank()) {
            report.setStatus("Pending");
        }
        if (report.getAdminComment() == null) {
            report.setAdminComment("");
        }
        return reportRepository.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public List<Report> getReportsByCitizenEmail(String email) {
        return reportRepository.findByCitizenEmail(email);
    }

    public Report updateReport(Long id, Report updatedReport) {
        Report report = reportRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Report not found"));

        report.setStatus(updatedReport.getStatus());
        report.setAdminComment(updatedReport.getAdminComment());

        return reportRepository.save(report);
    }

    public void deleteReport(Long id) {
        Report report = reportRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Report not found"));

        reportRepository.delete(report);
    }
}