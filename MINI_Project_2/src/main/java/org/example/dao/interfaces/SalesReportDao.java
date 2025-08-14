package org.example.dao.interfaces;

import org.example.model.SalesReport;

import java.util.List;

public interface SalesReportDao {
    void generateReport(SalesReport report);
    List<SalesReport> getAllReports();
}

