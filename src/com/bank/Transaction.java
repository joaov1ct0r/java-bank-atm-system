package com.bank;
public class Transaction {
    private final double value;
    private final String action;
    private final String holderName;

    public Transaction (Double value, String holderName, String action) {
        this.value = value;
        this.holderName = holderName;
        this.action = action;
    }
    protected double getValue() {
        return this.value;
    }
    protected String getAction() {
        return this.action;
    }

    protected String getHolderName() {
        return this.holderName;
    }
}
