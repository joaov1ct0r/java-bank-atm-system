package com.bank;

import java.util.ArrayList;

public class Account {
    private double balance;
    private final int pin;

    private final ArrayList<Transaction> transactions;
    public Account() {
        this.transactions = new ArrayList<>();
        this.setBalance();
        this.pin = this.generatePin();
    }
    private void setBalance() {
        this.balance = 0;
    }
    protected double getBalance() {
        return this.balance;
    }
    protected void addBalance(double value) {
        this.balance += value;
    }
    protected void subBalance(double value) {
        this.balance -= value;
    }
    private int generatePin() {
        return (int) (Math.random() * (1000 - 1 + 1) +1);
    }
    protected int getPin() {
        return this.pin;
    }

    protected void addTransaction(Double value, String holderName, String action) {
        Transaction transaction = new Transaction(value, holderName, action);
        this.transactions.add(transaction);
    }

    protected ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }
}
