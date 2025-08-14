package org.example.model;

import java.sql.Date;

public class SalesReport {
    private int reportId;
    private Date reportDate;
    private double totalSales;
    private int totalOrders;
    private String topItems;
    private int generatedBy;

    public SalesReport() {}

    public SalesReport(int reportId, Date reportDate, double totalSales, int totalOrders,
                       String topItems, int generatedBy) {
        this.reportId = reportId;
        this.reportDate = reportDate;
        this.totalSales = totalSales;
        this.totalOrders = totalOrders;
        this.topItems = topItems;
        this.generatedBy = generatedBy;
    }

    public int getReportId() { return reportId; }
    public void setReportId(int reportId) { this.reportId = reportId; }

    public Date getReportDate() { return reportDate; }
    public void setReportDate(Date reportDate) { this.reportDate = reportDate; }

    public double getTotalSales() { return totalSales; }
    public void setTotalSales(double totalSales) { this.totalSales = totalSales; }

    public int getTotalOrders() { return totalOrders; }
    public void setTotalOrders(int totalOrders) { this.totalOrders = totalOrders; }

    public String getTopItems() { return topItems; }
    public void setTopItems(String topItems) { this.topItems = topItems; }

    public int getGeneratedBy() { return generatedBy; }
    public void setGeneratedBy(int generatedBy) { this.generatedBy = generatedBy; }

    @Override
    public String toString() {
        return "SalesReport{" + "reportId=" + reportId + ", reportDate=" + reportDate +
                ", totalSales=" + totalSales + ", totalOrders=" + totalOrders +
                ", topItems='" + topItems + '\'' + ", generatedBy=" + generatedBy + '}';
    }
}
