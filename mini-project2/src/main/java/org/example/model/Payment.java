package org.example.model;

import java.sql.Timestamp;

public class Payment {
    private int paymentId;
    private int billId;
    private PaymentMethod paymentMethod;
    private double amountPaid;
    private Timestamp paymentTime;
    private Status status;

    public enum PaymentMethod {
        Cash,
        Card,
        UPI,
        Wallet
    }

    public enum Status {
        Successful,
        Failed
    }

    public Payment() {}

    public Payment(int paymentId, int billId, PaymentMethod paymentMethod, double amountPaid,
                   Timestamp paymentTime, Status status) {
        this.paymentId = paymentId;
        this.billId = billId;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.paymentTime = paymentTime;
        this.status = status;
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(double amountPaid) { this.amountPaid = amountPaid; }

    public Timestamp getPaymentTime() { return paymentTime; }
    public void setPaymentTime(Timestamp paymentTime) { this.paymentTime = paymentTime; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "Payment{" + "paymentId=" + paymentId + ", billId=" + billId +
                ", paymentMethod=" + paymentMethod + ", amountPaid=" + amountPaid +
                ", paymentTime=" + paymentTime + ", status=" + status + '}';
    }
}
