package org.example.dao.interfaces;

import org.example.model.Order;
import java.util.List;

public interface OrderDao {
    void addOrder(Order order);
    Order getOrderById(int orderId);
    List<Order> getAllOrders();
    void updateOrder(Order order);
    void deleteOrder(int orderId);
}
