package org.example.contoller;

import org.example.model.Customer;
import org.example.service.impl.CustomerServiceImpl;
import org.example.service.interfaces.CustomerService;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private final CustomerService customerService;
    private final Scanner scanner;

    public CustomerController() {
        this.customerService = new CustomerServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void addCustomer() {
        System.out.println("Enter Customer Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Customer Phone:");
        String phone = scanner.nextLine();

        System.out.println("Enter Customer Email:");
        String email = scanner.nextLine();

        Customer customer = new Customer(0, name, phone, email, true, new java.sql.Timestamp(System.currentTimeMillis()));
        customerService.addCustomer(customer);

        System.out.println("Customer added successfully!");
    }

    public void getCustomerById() {
        System.out.println("Enter Customer ID:");
        int customerId = scanner.nextInt();
        Customer customer = customerService.getCustomerById(customerId);

        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void listAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
