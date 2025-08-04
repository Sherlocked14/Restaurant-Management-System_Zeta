package org.example;

import org.example.dao.RestaurantDaoFactory;
import org.example.dao.interfaces.*;
import org.example.model.*;
import org.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection dbConnection = DatabaseUtil.getConnection()) {
            RestaurantDaoFactory factory = new RestaurantDaoFactory(dbConnection);
            Scanner inputReader = new Scanner(System.in);

            boolean isRunning = true;
            while (isRunning) {
                System.out.println("\n=== Restaurant Management System ===");
                System.out.println("1. User Management");
                System.out.println("2. Customer Management");
                System.out.println("3. Table Management");
                System.out.println("4. Order Management");
                System.out.println("5. Bill Management");
                System.out.println("6. Payment Management");
                System.out.println("7. Table Booking Management");
                System.out.println("8. Exit");
                System.out.print("Choose an option: ");
                int selectedOption = inputReader.nextInt();
                inputReader.nextLine();

                switch (selectedOption) {
                    case 1 -> manageUsers(factory, inputReader);
                    case 2 -> manageCustomers(factory, inputReader);
                    case 3 -> manageTables(factory, inputReader);
                    case 4 -> manageOrders(factory, inputReader);
                    case 5 -> manageBills(factory, inputReader);
                    case 6 -> managePayments(factory, inputReader);
                    case 7 -> manageTableBookings(factory, inputReader);
                    case 8 -> {
                        System.out.println("Exiting...");
                        isRunning = false;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void manageUsers(RestaurantDaoFactory factory, Scanner inputReader) {
        UserDao userRepository = factory.getUserDAO();

        boolean continueUserManagement = true;
        while (continueUserManagement) {
            System.out.println("\n=== User Management ===");
            System.out.println("1. Add User");
            System.out.println("2. View All Users");
            System.out.println("3. Update User Email");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int userChoice = inputReader.nextInt();
            inputReader.nextLine();

            switch (userChoice) {
                case 1:
                    User newUser = new User();
                    System.out.print("Username: ");
                    newUser.setUsername(inputReader.nextLine());
                    System.out.print("Password: ");
                    newUser.setPassword(inputReader.nextLine());
                    System.out.print("Email: ");
                    newUser.setEmail(inputReader.nextLine());
                    System.out.print("Phone: ");
                    newUser.setPhone(inputReader.nextLine());
                    System.out.print("Role (Manager/Waiter/KitchenStaff): ");
                    newUser.setRole(User.Role.valueOf(inputReader.nextLine()));
                    newUser.setActive(true);
                    newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    userRepository.addUser(newUser);
                    System.out.println("User added successfully.");
                    break;

                case 2:
                    List<User> allUsers = userRepository.getAllUsers();
                    System.out.println("---- Users ----");
                    for (User currentUser : allUsers) {
                        System.out.println(currentUser.getUserId() + ": " + currentUser.getUsername() + " | " + currentUser.getRole() + " | " + currentUser.getEmail());
                    }
                    break;

                case 3:
                    System.out.print("Enter User ID to update: ");
                    int userIdToUpdate = inputReader.nextInt();
                    inputReader.nextLine();
                    System.out.print("New Email: ");
                    String updatedEmail = inputReader.nextLine();
                    User existingUser = userRepository.getUserById(userIdToUpdate);
                    if (existingUser != null) {
                        existingUser.setEmail(updatedEmail);
                        userRepository.updateUser(existingUser);
                        System.out.println("Email updated.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter User ID to delete: ");
                    int userIdToDelete = inputReader.nextInt();
                    userRepository.deleteUser(userIdToDelete);
                    System.out.println("User deleted.");
                    break;

                case 5:
                    continueUserManagement = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void manageCustomers(RestaurantDaoFactory factory, Scanner inputReader) {
        CustomerDao customerRepository = factory.getCustomerDAO();

        boolean continueCustomerManagement = true;
        while (continueCustomerManagement) {
            System.out.println("\n=== Customer Management ===");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int customerChoice = inputReader.nextInt();
            inputReader.nextLine();

            switch (customerChoice) {
                case 1:
                    Customer newCustomer = new Customer();
                    System.out.print("Name: ");
                    newCustomer.setName(inputReader.nextLine());
                    System.out.print("Phone: ");
                    newCustomer.setPhone(inputReader.nextLine());
                    System.out.print("Email: ");
                    newCustomer.setEmail(inputReader.nextLine());
                    newCustomer.setActive(true);
                    newCustomer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    customerRepository.addCustomer(newCustomer);
                    System.out.println("Customer added successfully.");
                    break;

                case 2:
                    List<Customer> allCustomers = customerRepository.getAllCustomers();
                    System.out.println("---- Customers ----");
                    for (Customer currentCustomer : allCustomers) {
                        System.out.println(currentCustomer.getCustomerId() + ": " + currentCustomer.getName() + " | " + currentCustomer.getPhone() + " | " + currentCustomer.getEmail());
                    }
                    break;

                case 3:
                    System.out.print("Enter Customer ID to update: ");
                    int customerIdToUpdate = inputReader.nextInt();
                    inputReader.nextLine();
                    Customer existingCustomer = customerRepository.getCustomerById(customerIdToUpdate);
                    if (existingCustomer != null) {
                        System.out.print("New Name: ");
                        existingCustomer.setName(inputReader.nextLine());
                        System.out.print("New Phone: ");
                        existingCustomer.setPhone(inputReader.nextLine());
                        System.out.print("New Email: ");
                        existingCustomer.setEmail(inputReader.nextLine());
                        customerRepository.updateCustomer(existingCustomer);
                        System.out.println("Customer updated.");
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Customer ID to delete: ");
                    int customerIdToDelete = inputReader.nextInt();
                    customerRepository.deleteCustomer(customerIdToDelete);
                    System.out.println("Customer deleted.");
                    break;

                case 5:
                    continueCustomerManagement = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    private static void manageTables(RestaurantDaoFactory factory, Scanner inputReader) {
        TableDao tableRepository = factory.getTableDAO();

        boolean continueTableManagement = true;
        while (continueTableManagement) {
            System.out.println("\n=== Table Management ===");
            System.out.println("1. Add Table");
            System.out.println("2. View All Tables");
            System.out.println("3. Update Table");
            System.out.println("4. Delete Table");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int tableChoice = inputReader.nextInt();
            inputReader.nextLine();

            switch (tableChoice) {
                case 1 -> {
                    Table newTable = new Table();
                    System.out.print("Table Number: ");
                    newTable.setTableNumber(inputReader.nextInt());
                    System.out.print("Capacity: ");
                    newTable.setCapacity(inputReader.nextInt());
                    newTable.setStatus(Table.Status.Available);
                    tableRepository.addTable(newTable);
                    System.out.println("Table added successfully.");
                }
                case 2 -> {
                    List<Table> allTables = tableRepository.getAllTables();
                    System.out.println("---- Tables ----");
                    for (Table currentTable : allTables) {
                        System.out.println(currentTable.getTableId() + ": Table " + currentTable.getTableNumber() + " | Capacity: " + currentTable.getCapacity() + " | Status: " + currentTable.getStatus());
                    }
                }
                case 3 -> {
                    List<Table> tableList = tableRepository.getAllTables();
                    System.out.println("---- Tables ----");
                    for (Table currentTable : tableList) {
                        System.out.println(currentTable.getTableId() + ": Table " + currentTable.getTableNumber() + " | Capacity: " + currentTable.getCapacity() + " | Status: " + currentTable.getStatus());
                    }
                    System.out.print("Enter Table ID to update: ");
                    int tableIdToUpdate = inputReader.nextInt();
                    inputReader.nextLine();
                    Table existingTable = tableRepository.getTableById(tableIdToUpdate);
                    if (existingTable != null) {
                        System.out.print("New Table Number: ");
                        existingTable.setTableNumber(inputReader.nextInt());
                        System.out.print("New Capacity: ");
                        existingTable.setCapacity(inputReader.nextInt());
                        inputReader.nextLine();
                        System.out.print("Status (Available/Occupied/Booked/Reserved): ");
                        existingTable.setStatus(Table.Status.valueOf(inputReader.nextLine()));
                        tableRepository.updateTable(existingTable);
                        System.out.println("Table updated.");
                    } else {
                        System.out.println("Table not found.");
                    }
                }
                case 4 -> {
                    List<Table> tableList = tableRepository.getAllTables();
                    System.out.println("---- Tables ----");
                    for (Table currentTable : tableList) {
                        System.out.println(currentTable.getTableId() + ": Table " + currentTable.getTableNumber() + " | Capacity: " + currentTable.getCapacity() + " | Status: " + currentTable.getStatus());
                    }
                    System.out.print("Enter Table ID to delete: ");
                    int tableIdToDelete = inputReader.nextInt();
                    inputReader.nextLine();
                    tableRepository.deleteTable(tableIdToDelete);
                    System.out.println("Table deleted.");
                }
                case 5 -> {
                    continueTableManagement = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
    private static void manageOrders(RestaurantDaoFactory factory, Scanner inputReader) {
        OrderDao orderRepository = factory.getOrderDAO();
        TableDao tableRepository = factory.getTableDAO();

        boolean continueOrderManagement = true;
        while (continueOrderManagement) {
            System.out.println("\n=== Order Management ===");
            System.out.println("1. Add Order");
            System.out.println("2. View All Orders");
            System.out.println("3. Update Order Status");
            System.out.println("4. Delete Order");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int orderChoice = inputReader.nextInt();
            inputReader.nextLine();

            switch (orderChoice) {
                case 1:
                    List<Table> freeTablesList = tableRepository.getAllTables().stream()
                        .filter(table -> table.getStatus() == Table.Status.Available)
                        .toList();
                    
                    if (freeTablesList.isEmpty()) {
                        System.out.println("No available tables at the moment.");
                        break;
                    }
                    
                    System.out.println("---- Available Tables ----");
                    for (Table availableTable : freeTablesList) {
                        System.out.println("ID: " + availableTable.getTableId() + " | Table #: " + availableTable.getTableNumber() + " | Status: " + availableTable.getStatus());
                    }

                    Order newOrder = new Order();

                    System.out.print("Table ID: ");
                    int selectedTableId = inputReader.nextInt();
                    inputReader.nextLine();

                    Table chosenTable = tableRepository.getTableById(selectedTableId);
                    if (chosenTable == null || chosenTable.getStatus() != Table.Status.Available) {
                        System.out.println("Invalid Table ID or table is not available. Please select from available tables only.");
                        break;
                    }

                    System.out.print("Waiter ID: ");
                    newOrder.setWaiterId(inputReader.nextInt());
                    inputReader.nextLine();

                    newOrder.setTableId(selectedTableId);
                    newOrder.setOrderTime(new Timestamp(System.currentTimeMillis()));
                    newOrder.setStatus(Order.Status.Placed);
                    orderRepository.addOrder(newOrder);
                    
                    chosenTable.setStatus(Table.Status.Occupied);
                    tableRepository.updateTable(chosenTable);
                    
                    System.out.println("Order added successfully.");
                    break;

                case 2:
                    List<Order> allOrders = orderRepository.getAllOrders();
                    System.out.println("---- Orders ----");
                    for (Order currentOrder : allOrders) {
                        System.out.println(currentOrder.getOrderId() + ": Table " + currentOrder.getTableId() + " | Waiter: " + currentOrder.getWaiterId() + " | Status: " + currentOrder.getStatus());
                    }
                    break;

                case 3:
                    System.out.print("Enter Order ID to update: ");
                    int orderIdToUpdate = inputReader.nextInt();
                    inputReader.nextLine();

                    Order existingOrder = orderRepository.getOrderById(orderIdToUpdate);
                    if (existingOrder != null) {
                        System.out.print("New Status (Placed/Preparing/Served/Completed): ");
                        String statusInput = inputReader.nextLine().trim();

                        try {
                            Order.Status updatedStatus = Order.Status.valueOf(statusInput);
                            existingOrder.setStatus(updatedStatus);
                            orderRepository.updateOrder(existingOrder);
                            System.out.println("Order status updated.");
                        } catch (IllegalArgumentException ex) {
                            System.out.println("Invalid status. Please use one of: Placed, Preparing, Served, Completed.");
                        }
                    } else {
                        System.out.println("Order not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Order ID to delete: ");
                    int orderIdToDelete = inputReader.nextInt();
                    inputReader.nextLine();
                    orderRepository.deleteOrder(orderIdToDelete);
                    System.out.println("Order deleted.");
                    break;

                case 5:
                    continueOrderManagement = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void managePayments(RestaurantDaoFactory factory, Scanner inputReader) {
        PaymentDao paymentRepository = factory.getPaymentDAO();

        boolean continuePaymentManagement = true;
        while (continuePaymentManagement) {
            System.out.println("\n=== Payment Management ===");
            System.out.println("1. Record Payment");
            System.out.println("2. View All Payments");
            System.out.println("3. View Payment by Bill ID");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int paymentChoice = inputReader.nextInt();
            inputReader.nextLine();

            switch (paymentChoice) {
                case 1:
                    Payment newPayment = new Payment();
                    System.out.print("Bill ID: ");
                    newPayment.setBillId(inputReader.nextInt());
                    inputReader.nextLine();
                    System.out.print("Payment Method (Cash/Card/UPI/Wallet): ");
                    newPayment.setPaymentMethod(Payment.PaymentMethod.valueOf(inputReader.nextLine()));
                    System.out.print("Amount Paid: ");
                    newPayment.setAmountPaid(inputReader.nextDouble());
                    newPayment.setPaymentTime(new Timestamp(System.currentTimeMillis()));
                    newPayment.setStatus(Payment.Status.Successful);
                    paymentRepository.recordPayment(newPayment);
                    System.out.println("Payment recorded successfully.");
                    break;

                case 2:
                    List<Payment> allPayments = paymentRepository.getAllPayments();
                    System.out.println("---- Payments ----");
                    for (Payment currentPayment : allPayments) {
                        System.out.println(currentPayment.getPaymentId() + ": Bill " + currentPayment.getBillId() + " | Method: " + currentPayment.getPaymentMethod() + " | Amount: " + currentPayment.getAmountPaid());
                    }
                    break;

                case 3:
                    System.out.print("Enter Bill ID: ");
                    int billIdForPayment = inputReader.nextInt();
                    Payment paymentForBill = paymentRepository.getPaymentByBillId(billIdForPayment);
                    if (paymentForBill != null) {
                        System.out.println("Payment ID: " + paymentForBill.getPaymentId() + " | Amount: " + paymentForBill.getAmountPaid() + " | Method: " + paymentForBill.getPaymentMethod());
                    } else {
                        System.out.println("Payment not found for this bill.");
                    }
                    break;

                case 4:
                    continuePaymentManagement = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void manageTableBookings(RestaurantDaoFactory factory, Scanner inputReader) {
        TableBookingDao bookingRepository = factory.getTableBookingDAO();

        boolean continueBookingManagement = true;
        while (continueBookingManagement) {
            System.out.println("\n=== Table Booking Management ===");
            System.out.println("1. Add Booking");
            System.out.println("2. View All Bookings");
            System.out.println("3. Update Booking Status");
            System.out.println("4. Delete Booking");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int bookingChoice = inputReader.nextInt();
            inputReader.nextLine();

            switch (bookingChoice) {
                case 1:
                    TableBooking newBooking = new TableBooking();
                    System.out.print("Customer ID: ");
                    newBooking.setCustomerId(inputReader.nextInt());
                    System.out.print("Table ID: ");
                    newBooking.setTableId(inputReader.nextInt());
                    inputReader.nextLine();
                    System.out.print("Booking Date (YYYY-MM-DD): ");
                    newBooking.setBookingDate(Date.valueOf(inputReader.nextLine()));
                    System.out.print("Booking Time (HH:MM:SS): ");
                    newBooking.setBookingTime(Time.valueOf(inputReader.nextLine()));
                    newBooking.setStatus(TableBooking.Status.Confirmed);
                    newBooking.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    bookingRepository.addBooking(newBooking);
                    System.out.println("Booking added successfully.");
                    break;

                case 2:
                    List<TableBooking> allBookings = bookingRepository.getAllBookings();
                    System.out.println("---- Bookings ----");
                    for (TableBooking currentBooking : allBookings) {
                        System.out.println(currentBooking.getBookingId() + ": Customer " + currentBooking.getCustomerId() + " | Table " + currentBooking.getTableId() + " | Date: " + currentBooking.getBookingDate() + " | Status: " + currentBooking.getStatus());
                    }
                    break;

                case 3:
                    System.out.print("Enter Booking ID to update: ");
                    int bookingIdToUpdate = inputReader.nextInt();
                    inputReader.nextLine();
                    TableBooking existingBooking = bookingRepository.getBookingById(bookingIdToUpdate);
                    if (existingBooking != null) {
                        System.out.print("New Status (Confirmed/Cancelled/Completed): ");
                        existingBooking.setStatus(TableBooking.Status.valueOf(inputReader.nextLine()));
                        bookingRepository.updateBooking(existingBooking);
                        System.out.println("Booking status updated.");
                    } else {
                        System.out.println("Booking not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Booking ID to delete: ");
                    int bookingIdToDelete = inputReader.nextInt();
                    bookingRepository.deleteBooking(bookingIdToDelete);
                    System.out.println("Booking deleted.");
                    break;

                case 5:
                    continueBookingManagement = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void manageBills(RestaurantDaoFactory factory, Scanner inputReader) {
        BillDao billRepository = factory.getBillDAO();

        boolean continueBillManagement = true;
        while (continueBillManagement) {
            System.out.println("\n=== Bill Management ===");
            System.out.println("1. Generate Bill");
            System.out.println("2. View All Bills");
            System.out.println("3. View Bill by Order ID");
            System.out.println("4. View Unpaid Bills");
            System.out.println("5. Update Bill");
            System.out.println("6. Delete Bill");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int billChoice = inputReader.nextInt();
            inputReader.nextLine();

            switch (billChoice) {
                case 1:
                    Bill newBill = new Bill();
                    System.out.print("Order ID: ");
                    newBill.setOrderId(inputReader.nextInt());
                    System.out.print("Total Amount: ");
                    newBill.setTotalAmount(inputReader.nextDouble());
                    System.out.print("Discount: ");
                    newBill.setDiscount(inputReader.nextDouble());
                    System.out.print("Tax: ");
                    newBill.setTax(inputReader.nextDouble());
                    newBill.setFinalAmount(newBill.getTotalAmount() - newBill.getDiscount() + newBill.getTax());
                    newBill.setPaymentStatus(Bill.PaymentStatus.Unpaid);
                    newBill.setGeneratedAt(new Timestamp(System.currentTimeMillis()));
                    billRepository.generateBill(newBill);
                    System.out.println("Bill generated successfully.");
                    break;

                case 2:
                    List<Bill> outstandingBills = billRepository.getUnpaidBills();
                    System.out.println("---- Bills ----");
                    for (Bill currentBill : outstandingBills) {
                        System.out.println(currentBill.getBillId() + ": Order " + currentBill.getOrderId() + " | Total: " + currentBill.getTotalAmount() + " | Final: " + currentBill.getFinalAmount() + " | Status: " + currentBill.getPaymentStatus());
                    }
                    break;

                case 3:
                    System.out.print("Enter Order ID: ");
                    int orderIdForBill = inputReader.nextInt();
                    Bill billForOrder = billRepository.getBillByOrderId(orderIdForBill);
                    if (billForOrder != null) {
                        System.out.println("Bill ID: " + billForOrder.getBillId() + " | Total: " + billForOrder.getTotalAmount() + " | Final: " + billForOrder.getFinalAmount());
                    } else {
                        System.out.println("Bill not found for this order.");
                    }
                    break;

                case 4:
                    List<Bill> pendingBills = billRepository.getUnpaidBills();
                    System.out.println("---- Unpaid Bills ----");
                    for (Bill unpaidBill : pendingBills) {
                        System.out.println(unpaidBill.getBillId() + ": Order " + unpaidBill.getOrderId() + " | Final Amount: " + unpaidBill.getFinalAmount());
                    }
                    break;

                case 5:
                    System.out.print("Enter Bill ID to update: ");
                    int billIdToUpdate = inputReader.nextInt();
                    Bill existingBill = billRepository.getBillById(billIdToUpdate);
                    if (existingBill != null) {
                        System.out.print("New Total Amount: ");
                        existingBill.setTotalAmount(inputReader.nextDouble());
                        System.out.print("New Discount: ");
                        existingBill.setDiscount(inputReader.nextDouble());
                        System.out.print("New Tax: ");
                        existingBill.setTax(inputReader.nextDouble());
                        existingBill.setFinalAmount(existingBill.getTotalAmount() - existingBill.getDiscount() + existingBill.getTax());
                        billRepository.updateBill(existingBill);
                        System.out.println("Bill updated.");
                    } else {
                        System.out.println("Bill not found.");
                    }
                    break;

                case 6:
                    System.out.print("Enter Bill ID to delete: ");
                    int billIdToDelete = inputReader.nextInt();
                    billRepository.deleteBill(billIdToDelete);
                    System.out.println("Bill deleted.");
                    break;

                case 7:
                    continueBillManagement = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
