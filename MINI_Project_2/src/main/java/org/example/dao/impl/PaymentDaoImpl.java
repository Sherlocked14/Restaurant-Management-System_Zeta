package org.example.dao.impl;

import org.example.dao.interfaces.PaymentDao;
import org.example.model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    private final Connection connection;

    public PaymentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void recordPayment(Payment payment) {
        String sql = "INSERT INTO payments (bill_id, payment_method, amount_paid, paid_at, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, payment.getBillId());
            stmt.setString(2, payment.getPaymentMethod().toString());
            stmt.setDouble(3, payment.getAmountPaid());
            stmt.setTimestamp(4, payment.getPaymentTime());
            stmt.setString(5, payment.getStatus().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Payment getPaymentByBillId(int billId) {
        String sql = "SELECT * FROM payments WHERE bill_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, billId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setBillId(rs.getInt("bill_id"));
                payment.setPaymentMethod(Payment.PaymentMethod.valueOf(rs.getString("payment_method")));
                payment.setAmountPaid(rs.getDouble("amount_paid"));
                payment.setPaymentTime(rs.getTimestamp("paid_at"));
                return payment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Payment> getAllPayments() {
        String sql = "SELECT * FROM payments";
        List<Payment> payments = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setBillId(rs.getInt("bill_id"));
                payment.setPaymentMethod(Payment.PaymentMethod.valueOf(rs.getString("payment_method")));
                payment.setAmountPaid(rs.getDouble("amount_paid"));
                payment.setPaymentTime(rs.getTimestamp("paid_at"));
                payment.setStatus(Payment.Status.valueOf(rs.getString("status")));
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
}
