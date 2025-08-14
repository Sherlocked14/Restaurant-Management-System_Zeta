package org.example.contoller;

import org.example.model.Payment;
import org.example.service.impl.PaymentServiceImpl;
import org.example.service.interfaces.PaymentService;
import java.util.Scanner;

public class PaymentController {
    private final PaymentService paymentService;
    private final Scanner scanner;

    public PaymentController() {
        this.paymentService = new PaymentServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void makePayment() {
        System.out.println("Enter Bill ID:");
        int billId = scanner.nextInt();

        System.out.println("Enter Payment Amount:");
        double amount = scanner.nextDouble();

        System.out.println("Enter Payment Method (Cash/Card/UPI/Wallet):");
        String method = scanner.next();

        Payment payment = new Payment(0, billId, Payment.PaymentMethod.valueOf(method),
                amount, new java.sql.Timestamp(System.currentTimeMillis()), Payment.Status.Successful);

        paymentService.processPayment(payment);
        System.out.println("Payment processed successfully!");
    }

    public void viewPaymentById() {
        System.out.println("Enter Payment ID:");
        int paymentId = scanner.nextInt();
        Payment payment = paymentService.getPaymentByBillId(paymentId);

        if (payment != null) {
            System.out.println(payment);
        } else {
            System.out.println("Payment not found.");
        }
    }
}
