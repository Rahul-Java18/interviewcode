package com.abc;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty.");
        }
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    // Method to open a new account for the customer
    public Customer openAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
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
<<<<<<< HEAD
    // Method to generate a statement detailing all accounts and transactions
    public String getStatement() {
        String statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();// Sum the total transactions for all accounts
=======
    public List<TransactionSummary> getTransactionSummary() {
        List<TransactionSummary> summaries = new ArrayList<>();
        for (Account a : accounts) {
            TransactionSummary summary = new TransactionSummary(accountTypeName(a));
            double total = 0.0;

            for (Transaction t : a.transactions) {
                String type = (t.amount < 0) ? "withdrawal" : "deposit";
                String amount = toDollars(t.amount);
                summary.addTransaction(new TransactionDetail(type, amount));
                total += t.amount;
            }
            summary.setTotal(toDollars(total));
            summaries.add(summary);
>>>>>>> 595dc4151318720f3b1092676d28045cf085d9c5
        }
        return summaries;
    }
    private String accountTypeName(Account account) {
        switch (account.getAccountType()) {
            case Account.CHECKING: return "Checking Account";
            case Account.SAVINGS: return "Savings Account";
            case Account.MAXI_SAVINGS: return "Maxi Savings Account";
            case Account.SUPER_SAVINGS: return "Super Savings Account";
            default: return "Unknown Account Type";
        }
    }
//for doller format
    private String toDollars(double d) {
        return String.format("$%,.2f", abs(d));
    }
<<<<<<< HEAD

    // New transfer method
//    public void transfer(Account fromAccount, Account toAccount, double amount) {
//        fromAccount.withdraw(amount);
//        toAccount.deposit(amount);
//    }
//}
=======
>>>>>>> 595dc4151318720f3b1092676d28045cf085d9c5
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
