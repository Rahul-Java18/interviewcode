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

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }
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

    private String toDollars(double d) {
        return String.format("$%,.2f", abs(d));
    }

    public void transfer(Account fromAccount, Account toAccount, double amount) {
        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
}
