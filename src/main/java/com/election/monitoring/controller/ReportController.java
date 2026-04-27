package com.election.monitoring.controller;

import com.election.monitoring.entity.Report;
import com.election.monitoring.service.ReportService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:5173")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.saveReport(report);
    }

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/citizen/{email}")
    public List<Report> getReportsByCitizen(@PathVariable String email) {
        return reportService.getReportsByCitizenEmail(email);
    }

    @PutMapping("/{id}")
    public Report updateReport(@PathVariable Long id, @RequestBody Report updatedReport) {
        return reportService.updateReport(id, updatedReport);
    }

    @DeleteMapping("/{id}")
    public String deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return "Report deleted successfully";
    }
}