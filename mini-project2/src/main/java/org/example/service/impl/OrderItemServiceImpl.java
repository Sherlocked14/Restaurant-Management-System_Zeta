package org.example.service.impl;
import org.example.model.OrderItem;
import org.example.service.interfaces.OrderItemService;
import java.util.ArrayList;
import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        // Logic to add order item to DB
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        // Fetch order items by order ID
        return new ArrayList<>();
    }

    @Override
    public void updateOrderItemStatus(int orderItemId, String status) {
        // Update order item status
    }
}
