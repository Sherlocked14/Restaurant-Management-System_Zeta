package org.example.contoller;

import org.example.model.Order;
import org.example.service.impl.OrderServiceImpl;
import org.example.service.interfaces.OrderService;
import java.util.Scanner;

public class OrderController {
    private final OrderService orderService;
    private final Scanner scanner;

    public OrderController() {
        this.orderService = new OrderServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void placeOrder() {
        System.out.println("Enter Table ID:");
        int tableId = scanner.nextInt();

        System.out.println("Enter Waiter ID:");
        int waiterId = scanner.nextInt();

        Order order = new Order(0, tableId, waiterId, new java.sql.Timestamp(System.currentTimeMillis()), Order.Status.Placed);
        orderService.placeOrder(order);

        System.out.println("Order placed successfully!");
    }

    public void viewOrderById() {
        System.out.println("Enter Order ID:");
        int orderId = scanner.nextInt();
        Order order = orderService.getOrderById(orderId);

        if (order != null) {
            System.out.println(order);
        } else {
            System.out.println("Order not found.");
        }
    }
}
