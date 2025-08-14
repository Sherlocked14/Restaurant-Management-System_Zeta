package org.example.service.impl;

import org.example.model.SalesReport;
import org.example.service.interfaces.SalesReportService;

import java.time.LocalDate;

public class SalesReportServiceImpl implements SalesReportService {
    @Override
    public void generateReport(SalesReport report) {
        // Generate sales report
    }

    @Override
    public SalesReport getReportByDate(LocalDate date) {
        // Fetch sales report by date
        return null;
    }

    @Override
    public SalesReport getReportById(int reportId) {
        // Implementation to fetch report by ID
        return null;
    }
}
