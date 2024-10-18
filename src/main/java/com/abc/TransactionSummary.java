package com.abc;

import java.util.ArrayList;
import java.util.List;
//To get transactions summary
public class TransactionSummary {
    private String accountType;
    private List<TransactionDetail> transactions;
    private String total;

    public TransactionSummary(String accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<>();
        this.total = "$0.00"; // Initialize with default total
    }

    public String getAccountType() {
        return accountType;
    }

    public List<TransactionDetail> getTransactions() {
        return transactions;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void addTransaction(TransactionDetail transaction) {
        transactions.add(transaction);
    }
}
//transactions of individual account details
class TransactionDetail {
    private String type;
    private String amount;

    public TransactionDetail(String type, String amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }
}

