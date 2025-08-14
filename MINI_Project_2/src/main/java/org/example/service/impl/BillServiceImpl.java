package org.example.service.impl;


import org.example.model.Bill;
import org.example.service.interfaces.BillService;

public class BillServiceImpl implements BillService {
    @Override
    public void generateBill(Bill bill) {
        // Generate bill
    }

    @Override
    public Bill getBillByOrderId(int orderId) {
        // Get bill for given order ID
        return null;
    }

    @Override
    public void updatePaymentStatus(int billId, String paymentStatus) {
        // Update payment status for bill
    }
}

