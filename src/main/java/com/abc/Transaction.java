package com.abc;

import java.util.Calendar;
import java.util.Date;
//for financial transaction
public class Transaction {
    public final double amount;
    private Date transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().now();
    }
}
