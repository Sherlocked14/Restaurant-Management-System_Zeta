package org.example.dao.interfaces;

import org.example.model.TableBooking;
import java.util.List;

public interface TableBookingDao {
    void addBooking(TableBooking booking);
    TableBooking getBookingById(int bookingId);
    List<TableBooking> getAllBookings();
    void updateBooking(TableBooking booking);
    void deleteBooking(int bookingId);
}
