package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<>();
    }
    // Method to add a new customer to the bank
    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        customers.add(customer);
    }
    // Method to generate a summary of all customers in the bank
    public String customerSummary() {
        StringBuilder summary = new StringBuilder("Customer Summary");
        for (Customer c : customers)
            summary.append("\n - ").append(c.getName()).append(" (").append(format(c.getNumberOfAccounts(), "account")).append(")");
        return summary.toString();
    }

    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }
    // Method to calculate the total interest paid by the bank to all customers
    public double totalInterestPaid() {
        double total = 0;
        for (Customer c : customers)
            total += c.totalInterestEarned();// Add customer's total interest to the total
        return total;//total intrest
    }
    // Method to get the name of the first customer in the list
    public String getFirstCustomer() {
        try {
            return customers.get(0).getName();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
