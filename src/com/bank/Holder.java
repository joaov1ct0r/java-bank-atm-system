package com.bank;
public class Holder {
    private final String name;
    protected final Account account;
    public Holder(String name) {
        this.name = name;
        this.account = new Account();
    }
    protected String getName() {
        return this.name;
    }
}
