package org.example.dao.interfaces;

import org.example.model.Payment;
import java.util.List;

public interface PaymentDao {
    void recordPayment(Payment payment);
    Payment getPaymentByBillId(int billId);
    List<Payment> getAllPayments();
}
