package org.example.service.interfaces;

import org.example.model.SalesReport;

import java.time.LocalDate;

public interface SalesReportService {
    void generateReport(SalesReport report);
    SalesReport getReportByDate(LocalDate date);
    SalesReport getReportById(int reportId);
}
