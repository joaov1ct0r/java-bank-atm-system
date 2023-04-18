package com.bank;
import java.util.ArrayList;
import java.util.Optional;

public class Bank {
    protected ArrayList<Holder> holders;
    protected ArrayList<Transaction> transactions;
    protected final ATM atm;
    public Bank() {
        this.holders = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.atm = new ATM(this);
    }

    protected Holder findHolder(String name) {
        Optional<Holder> isUserHolder = this.holders
                .stream()
                .filter(holder -> holder.getName().equals(name))
                .findFirst();

        return isUserHolder.orElse(null);

    }
    protected void addTransaction(Double value, String holderName, String action) {
        Transaction transaction = new Transaction(value, holderName, action);
        this.transactions.add(transaction);
    }
}