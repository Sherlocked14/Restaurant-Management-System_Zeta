package org.example.dao.impl;

import org.example.dao.interfaces.BillDao;
import org.example.model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BilDaoImpl implements BillDao {
    private final Connection connection;

    public BilDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void generateBill(Bill bill) {
        String sql = "INSERT INTO bills (order_id, total_amount, discount, tax, final_amount, payment_status, generated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bill.getOrderId());
            stmt.setDouble(2, bill.getTotalAmount());
            stmt.setDouble(3, bill.getDiscount());
            stmt.setDouble(4, bill.getTax());
            stmt.setDouble(5, bill.getFinalAmount());
            stmt.setString(6, bill.getPaymentStatus().toString());
            stmt.setTimestamp(7, bill.getGeneratedAt());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bill getBillByOrderId(int orderId) {
        String sql = "SELECT * FROM bills WHERE order_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("bill_id"));
                bill.setOrderId(rs.getInt("order_id"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bill.setGeneratedAt(rs.getTimestamp("generated_at"));
                return bill;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateBill(Bill bill) {
        String sql = "UPDATE bills SET total_amount=?, discount=?, tax=?, final_amount=?, payment_status=?, generated_at=? WHERE bill_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, bill.getTotalAmount());
            stmt.setDouble(2, bill.getDiscount());
            stmt.setDouble(3, bill.getTax());
            stmt.setDouble(4, bill.getFinalAmount());
            stmt.setString(5, bill.getPaymentStatus().toString());
            stmt.setTimestamp(6, bill.getGeneratedAt());
            stmt.setInt(7, bill.getBillId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBill(int billId) {
        String sql = "DELETE FROM bills WHERE bill_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bill getBillById(int billId) {
        String sql = "SELECT * FROM bills WHERE bill_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("bill_id"));
                bill.setOrderId(rs.getInt("order_id"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bill.setFinalAmount(rs.getDouble("final_amount"));
                bill.setPaymentStatus(Bill.PaymentStatus.valueOf(rs.getString("payment_status")));
                bill.setGeneratedAt(rs.getTimestamp("generated_at"));
                return bill;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Bill> getUnpaidBills() {
        String sql = "SELECT * FROM bills WHERE payment_status = 'Unpaid'";
        List<Bill> bills = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("bill_id"));
                bill.setOrderId(rs.getInt("order_id"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bill.setFinalAmount(rs.getDouble("final_amount"));
                bill.setPaymentStatus(Bill.PaymentStatus.valueOf(rs.getString("payment_status")));
                bill.setGeneratedAt(rs.getTimestamp("generated_at"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
}
