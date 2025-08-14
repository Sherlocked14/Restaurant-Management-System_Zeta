package org.example.dao;

import org.example.dao.impl.*;
import org.example.dao.interfaces.*;

import java.sql.Connection;

public class RestaurantDaoFactory {

    private final Connection connection;

    public RestaurantDaoFactory(Connection connection) {
        this.connection = connection;
    }

    public UserDao getUserDAO() {
        return new UserDaoImpl(connection);
    }

    public CustomerDao getCustomerDAO() {
        return new CustomerDaoImpl(connection);
    }

    public TableDao getTableDAO() {
        return new TableDaoImpl(connection);
    }

    public TableBookingDao getTableBookingDAO() {
        return new TableBookingDaoImpl(connection);
    }

    public MenuItemDao getMenuItemDAO() {
        return new MenuItemDaoImpl(connection);
    }

    public OrderDao getOrderDAO() {
        return new OrderDaoImpl(connection);
    }

    public OrderItemDao getOrderItemDAO() {
        return new OrderItemDaoImpl(connection);
    }

    public BillDao getBillDAO() {
        return new BilDaoImpl(connection);
    }

    public PaymentDao getPaymentDAO() {
        return new PaymentDaoImpl(connection);
    }

    public EmployeeDao getEmployeeDAO() {
        return new EmployeeDaoImpl(connection);
    }

    public SalesReportDao getSalesReportDAO() {
        return new SalesReportDaoImpl(connection);
    }
}
