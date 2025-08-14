package org.example.service.interfaces;
import org.example.model.Payment;

public interface PaymentService {
    void processPayment(Payment payment);
    Payment getPaymentByBillId(int billId);
}
