package com.abc;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    // Method to open a new account for the customer
    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }
    // Method to calculate the total interest earned across all accounts
    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }
    // Method to generate a statement detailing all accounts and transactions
    public String getStatement() {
        String statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();// Sum the total transactions for all accounts
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        String s = "";

        // Translate to pretty account type
        switch (a.getAccountType()) {
            case Account.CHECKING:
                s += "Checking Account\n";
                break;
            case Account.SAVINGS:
                s += "Savings Account\n";
                break;
            case Account.MAXI_SAVINGS:
                s += "Maxi Savings Account\n";
                break;
            case Account.SUPER_SAVINGS: // Handle the new account type
                s += "Super Savings Account\n";
                break;
        }

        // Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + toDollars(total);
        return s;
    }
//for doller format
    private String toDollars(double d) {
        return String.format("$%,.2f", abs(d));
    }

    // New transfer method
//    public void transfer(Account fromAccount, Account toAccount, double amount) {
//        fromAccount.withdraw(amount);
//        toAccount.deposit(amount);
//    }
//}
    // New transfer method to transfer funds between accounts
    public void transfer(Account fromAccount, Account toAccount, double amount) {
        //checking for null accounts
        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        //withdraw and deposit
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
}
