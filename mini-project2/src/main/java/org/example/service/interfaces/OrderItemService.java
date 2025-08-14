package org.example.service.interfaces;
import org.example.model.OrderItem;
import java.util.List;
public interface OrderItemService {
    void addOrderItem(OrderItem orderItem);
    List<OrderItem> getOrderItemsByOrderId(int orderId);
    void updateOrderItemStatus(int orderItemId, String status);
}