package org.example.dao.interfaces;

import org.example.model.OrderItem;
import java.util.List;

public interface OrderItemDao {
    void addOrderItem(OrderItem orderItem);
    List<OrderItem> getOrderItemsByOrderId(int orderId);
    void updateOrderItem(OrderItem orderItem);
    void deleteOrderItem(int orderItemId);
}
