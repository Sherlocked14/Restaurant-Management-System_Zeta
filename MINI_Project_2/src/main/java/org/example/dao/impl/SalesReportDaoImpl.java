package org.example.dao.impl;

import org.example.dao.interfaces.SalesReportDao;
import org.example.model.SalesReport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesReportDaoImpl implements SalesReportDao {
    private final Connection connection;

    public SalesReportDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void generateReport(SalesReport report) {
        String sql = "INSERT INTO sales_reports (report_date, total_sales, total_orders) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, report.getReportDate());
            stmt.setDouble(2, report.getTotalSales());
            stmt.setInt(3, report.getTotalOrders());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SalesReport> getAllReports() {
        String sql = "SELECT * FROM sales_reports";
        List<SalesReport> reports = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                SalesReport report = new SalesReport();
                report.setReportId(rs.getInt("report_id"));
                report.setReportDate(rs.getDate("report_date"));
                report.setTotalSales(rs.getDouble("total_sales"));
                report.setTotalOrders(rs.getInt("total_orders"));
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }
}
