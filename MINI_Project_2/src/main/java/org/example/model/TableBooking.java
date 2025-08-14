package org.example.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class TableBooking {
    private int bookingId;
    private int customerId;
    private int tableId;
    private Date bookingDate;
    private Time bookingTime;
    private Status status;
    private Timestamp createdAt;

    public enum Status {
        Confirmed,
        Cancelled,
        Completed
    }

    public TableBooking() {}

    public TableBooking(int bookingId, int customerId, int tableId, Date bookingDate, Time bookingTime, Status status, Timestamp createdAt) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.tableId = tableId;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getTableId() { return tableId; }
    public void setTableId(int tableId) { this.tableId = tableId; }

    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }

    public Time getBookingTime() { return bookingTime; }
    public void setBookingTime(Time bookingTime) { this.bookingTime = bookingTime; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "TableBooking{" + "bookingId=" + bookingId + ", customerId=" + customerId +
                ", tableId=" + tableId + ", bookingDate=" + bookingDate +
                ", bookingTime=" + bookingTime + ", status=" + status +
                ", createdAt=" + createdAt + '}';
    }
}
