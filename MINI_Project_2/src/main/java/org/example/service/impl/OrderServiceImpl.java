package org.example.service.impl;


import org.example.model.Order;
import org.example.service.interfaces.OrderService;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private List<Order> orders = new ArrayList<>();

    @Override
    public void placeOrder(Order order) {
        orders.add(order);
    }

    @Override
    public Order getOrderById(int orderId) {
        return orders.stream().filter(o -> o.getOrderId() == orderId).findFirst().orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public void updateOrder(Order order) {
        deleteOrder(order.getOrderId());
        placeOrder(order);
    }

    @Override
    public void deleteOrder(int orderId) {
        orders.removeIf(o -> o.getOrderId() == orderId);
    }
}