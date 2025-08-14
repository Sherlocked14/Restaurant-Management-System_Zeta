package org.example.contoller;



import org.example.model.Table;
import org.example.model.TableBooking;
import org.example.service.impl.TableServiceImpl;
import org.example.service.interfaces.TableService;
import java.util.Scanner;

public class TableBookingController {
    private final TableService tableBookingService;
    private final Scanner scanner;

    public TableBookingController() {
        this.tableBookingService = new TableServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void bookTable() {
        System.out.println("Enter Customer ID:");
        int customerId = scanner.nextInt();

        System.out.println("Enter Table ID:");
        int tableId = scanner.nextInt();

        System.out.println("Enter Booking Date (YYYY-MM-DD):");
        String date = scanner.next();

        System.out.println("Enter Booking Time (HH:MM:SS):");
        String time = scanner.next();

        TableBooking tableBooking = new TableBooking(0, customerId, tableId, java.sql.Date.valueOf(date),
                java.sql.Time.valueOf(time), TableBooking.Status.Confirmed, new java.sql.Timestamp(System.currentTimeMillis()));

        tableBookingService.bookTable(tableBooking);
        System.out.println("Table booked successfully!");
    }

    public void viewBookingById() {
        System.out.println("Enter Booking ID:");
        int bookingId = scanner.nextInt();
        Table tableBooking = tableBookingService.getTableById(bookingId);

        if (tableBooking != null) {
            System.out.println(tableBooking);
        } else {
            System.out.println("Booking not found.");
        }
    }
}

