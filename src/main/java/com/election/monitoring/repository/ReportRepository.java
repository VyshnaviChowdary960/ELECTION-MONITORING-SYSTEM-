package com.election.monitoring.repository;

import com.election.monitoring.entity.Report;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByCitizenEmail(String citizenEmail);
}