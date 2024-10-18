package com.abc;

import java.util.ArrayList;
import java.util.List;

public class Account {
    // Constants representing different account types for more secure
    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;
    public static final int SUPER_SAVINGS = 3; // New account type

    private final int accountType;// Type of account (checking, savings, etc.)
    // List to hold the transactions associated with this account
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<>();
    }
    // Method to deposit an amount into the account
    public void deposit(double amount) {
        // to Check that the deposit amount is positive
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        // Created a new transaction for the deposit and add it to the transactions list
        transactions.add(new Transaction(amount));
    }
    // Method to withdraw an amount from the account
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero");
        }
        // CheckING if there are sufficient funds for the withdrawal
        if (sumTransactions() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        transactions.add(new Transaction(-amount));
    }
    // Method to calculate the interest earned based on the account type and balance
    public double interestEarned() {
        double amount = sumTransactions();
        switch (accountType) {
            // Interest calculation for Savings accounts
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;// 0.1% interest for amounts up to $1000
                else
                    return 1 + (amount - 1000) * 0.002;// 0.2% interest for amounts above $1000
            case MAXI_SAVINGS:
                if (amount <= 1000)
                    return amount * 0.02;// 2% interest for amounts up to $1000
                if (amount <= 2000)
                    return 20 + (amount - 1000) * 0.05;// 5% interest for amounts between $1000 and $2000
                return 70 + (amount - 2000) * 0.1;// 10% interest for amounts above $2000
            case SUPER_SAVINGS:
                if (amount <= 1000)
                    return amount * 0.04;// 4% interest for amounts up to $1000
                if (amount <= 2000)
                    return 40 + (amount - 1000) * 0.07; // 7% interest for amounts between $1000 and $2000
                return 110 + (amount - 2000) * 0.12; // 12% interest for amounts above $2000
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
        return checkIfTransactionsExist(true);
    }
    // method to check if transactions exist and sum them
    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t : transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

    // Method to transfer an amount from one account to another
    public void transfer(Account fromAccount, Account toAccount, double amount) {
        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
}
