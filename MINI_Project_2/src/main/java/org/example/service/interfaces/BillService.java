package org.example.service.interfaces;

import org.example.model.Bill;

public interface BillService {
    void generateBill(Bill bill);
    Bill getBillByOrderId(int orderId);
    void updatePaymentStatus(int billId, String paymentStatus);
}
