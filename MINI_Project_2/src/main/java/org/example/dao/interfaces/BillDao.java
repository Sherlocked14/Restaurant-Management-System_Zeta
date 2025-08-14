package org.example.dao.interfaces;

import org.example.model.Bill;
import java.util.List;

public interface BillDao {
    void generateBill(Bill bill);
    Bill getBillByOrderId(int orderId);
    Bill getBillById(int billId);
    List<Bill> getUnpaidBills();
    void updateBill(Bill bill);
    void deleteBill(int billId);
}
