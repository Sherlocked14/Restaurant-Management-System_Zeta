package org.example.dao.impl;

import org.example.dao.interfaces.TableBookingDao;
import org.example.model.TableBooking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableBookingDaoImpl implements TableBookingDao {
    private Connection connection;

    public TableBookingDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addBooking(TableBooking booking) {
        String sql = "INSERT INTO table_bookings (customer_id, table_id, booking_date, booking_time, status, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, booking.getCustomerId());
            stmt.setInt(2, booking.getTableId());
            stmt.setDate(3, booking.getBookingDate());
            stmt.setTime(4, booking.getBookingTime());
            stmt.setString(5, booking.getStatus().toString());
            stmt.setTimestamp(6, booking.getCreatedAt());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TableBooking getBookingById(int bookingId) {
        String sql = "SELECT * FROM table_bookings WHERE booking_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToBooking(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TableBooking> getAllBookings() {
        String sql = "SELECT * FROM table_bookings";
        List<TableBooking> bookings = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                bookings.add(mapRowToBooking(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public void updateBooking(TableBooking booking) {
        String sql = "UPDATE table_bookings SET customer_id=?, table_id=?, booking_date=?, booking_time=?, status=? WHERE booking_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, booking.getCustomerId());
            stmt.setInt(2, booking.getTableId());
            stmt.setDate(3, booking.getBookingDate());
            stmt.setTime(4, booking.getBookingTime());
            stmt.setString(5, booking.getStatus().toString());
            stmt.setInt(6, booking.getBookingId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBooking(int bookingId) {
        String sql = "DELETE FROM table_bookings WHERE booking_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TableBooking mapRowToBooking(ResultSet rs) throws SQLException {
        TableBooking booking = new TableBooking();
        booking.setBookingId(rs.getInt("booking_id"));
        booking.setCustomerId(rs.getInt("customer_id"));
        booking.setTableId(rs.getInt("table_id"));
        booking.setBookingDate(rs.getDate("booking_date"));
        booking.setBookingTime(rs.getTime("booking_time"));
        booking.setStatus(TableBooking.Status.valueOf(rs.getString("status")));
        booking.setCreatedAt(rs.getTimestamp("created_at"));
        return booking;
    }
}
