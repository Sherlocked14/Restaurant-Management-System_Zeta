package org.example.model;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int tableId;
    private int waiterId;
    private Timestamp orderTime;
    private Status status;

    public enum Status {
        Placed,
        Preparing,
        Served,
        Completed
    }

    public Order() {}

    public Order(int orderId, int tableId, int waiterId, Timestamp orderTime, Status status) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.waiterId = waiterId;
        this.orderTime = orderTime;
        this.status = status;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getTableId() { return tableId; }
    public void setTableId(int tableId) { this.tableId = tableId; }

    public int getWaiterId() { return waiterId; }
    public void setWaiterId(int waiterId) { this.waiterId = waiterId; }

    public Timestamp getOrderTime() { return orderTime; }
    public void setOrderTime(Timestamp orderTime) { this.orderTime = orderTime; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", tableId=" + tableId +
                ", waiterId=" + waiterId + ", orderTime=" + orderTime +
                ", status=" + status + '}';
    }
}
