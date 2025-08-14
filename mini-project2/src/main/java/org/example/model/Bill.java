package org.example.model;

import java.sql.Timestamp;

public class Bill {
    private int billId;
    private int orderId;
    private double totalAmount;
    private double discount;
    private double tax;
    private double finalAmount;
    private PaymentStatus paymentStatus;
    private Timestamp generatedAt;

    public enum PaymentStatus {
        Paid,
        Unpaid
    }

    public Bill() {}

    public Bill(int billId, int orderId, double totalAmount, double discount,
                double tax, double finalAmount, PaymentStatus paymentStatus, Timestamp generatedAt) {
        this.billId = billId;
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.tax = tax;
        this.finalAmount = finalAmount;
        this.paymentStatus = paymentStatus;
        this.generatedAt = generatedAt;
    }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public double getTax() { return tax; }
    public void setTax(double tax) { this.tax = tax; }

    public double getFinalAmount() { return finalAmount; }
    public void setFinalAmount(double finalAmount) { this.finalAmount = finalAmount; }

    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }

    public Timestamp getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(Timestamp generatedAt) { this.generatedAt = generatedAt; }

    @Override
    public String toString() {
        return "Bill{" + "billId=" + billId + ", orderId=" + orderId +
                ", totalAmount=" + totalAmount + ", discount=" + discount +
                ", tax=" + tax + ", finalAmount=" + finalAmount +
                ", paymentStatus=" + paymentStatus + ", generatedAt=" + generatedAt + '}';
    }
}
