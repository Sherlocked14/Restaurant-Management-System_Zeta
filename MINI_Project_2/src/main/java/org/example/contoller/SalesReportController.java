package org.example.contoller;

import org.example.model.SalesReport;
import org.example.service.interfaces.SalesReportService;
import org.example.service.impl.SalesReportServiceImpl;

import java.util.Scanner;

public class SalesReportController {
    private final SalesReportService salesReportService;
    private final Scanner scanner;

    public SalesReportController() {
        this.salesReportService = new SalesReportServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void generateReport() {
        System.out.println("Enter Report Date (YYYY-MM-DD):");
        String date = scanner.next();

        System.out.println("Enter Total Sales:");
        double totalSales = scanner.nextDouble();

        System.out.println("Enter Total Orders:");
        int totalOrders = scanner.nextInt();

        System.out.println("Enter Top Items (comma separated):");
        String topItems = scanner.next();

        SalesReport report = new SalesReport(0, java.sql.Date.valueOf(date), totalSales,
                totalOrders, topItems, 1); // assuming '1' is the user ID of the admin

        salesReportService.generateReport(report);
        System.out.println("Sales report generated successfully!");
    }

    public void viewReportById() {
        System.out.println("Enter Report ID:");
        int reportId = scanner.nextInt();
        SalesReport report = salesReportService.getReportById(reportId);

        if (report != null) {
            System.out.println(report);
        } else {
            System.out.println("Report not found.");
        }
    }
}
