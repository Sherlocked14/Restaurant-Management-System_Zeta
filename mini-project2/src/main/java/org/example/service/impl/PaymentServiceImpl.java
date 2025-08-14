package org.example.service.impl;

import org.example.model.Payment;
import org.example.service.interfaces.PaymentService;


public class PaymentServiceImpl implements PaymentService {
    @Override
    public void processPayment(Payment payment) {
        // Process the payment
    }

    @Override
    public Payment getPaymentByBillId(int billId) {
        // Fetch payment for the bill
        return null;
    }
}
