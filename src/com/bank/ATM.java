package com.bank;
import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private final Scanner scanner;
    private final Bank bank;
    public ATM (Bank bank) {
        this.scanner = new Scanner(System.in);
        this.bank = bank;
    }

    protected void helloWorld() {
        System.out.println("**********");
        System.out.println("Enter the number of the option you want: ");
        System.out.println("1: Exit");
        System.out.println("2: Create holder");
        System.out.println("3: Login as holder");
        System.out.println("4: List bank transactions");
        System.out.println("5: List bank holders");
        System.out.println("**********");

        int option = this.scanner.nextInt();
        this.scanner.nextLine();

        if (option == 1) {
            System.exit(1);
        } else if (option == 2) {
            this.createHolder();
        } else if (option == 3) {
            this.holderLogin();
        } else if (option == 4) {
            this.bankTransactions(this.bank);
        } else if (option == 5) {
            this.bankHolders(this.bank);
        } else {
            System.out.println("**********");
            System.out.println("***** Option not defined *****");
            System.out.println("**********");
            System.exit(1);
        }
    }

    private void bankTransactions(Bank bank) {
        ArrayList<Transaction> transactions = bank.transactions;

        System.out.println("**********");
        System.out.println("Bank transactions: ");

        for (Transaction transaction : transactions) {
            System.out.println("**********");
            System.out.println("Holder name: " + transaction.getHolderName());
            System.out.println("Action: " + transaction.getAction());
            System.out.println("Value: " + transaction.getValue());
            System.out.println("**********");
        }

        System.out.println("**********");

        this.helloWorld();
    }

    private void bankHolders(Bank bank) {
        ArrayList<Holder> holders = bank.holders;

        System.out.println("**********");
        System.out.println("Bank holders: ");

        for (Holder holder : holders) {
            System.out.println("**********");
            System.out.println("Holder name: " + holder.getName());
            System.out.println("**********");
        }

        System.out.println("**********");

        this.helloWorld();
    }

    private void createHolder() {
        System.out.println("**********");
        System.out.println("Enter your name: ");
        System.out.println("**********");

        String holder = this.scanner.nextLine();
        Holder isUserHolder = this.bank.findHolder(holder);

        System.out.println("**********");

        if (isUserHolder != null) {
            System.out.println("Holder name already in use!");
            System.out.println("**********");
            System.exit(1);
        }

        Holder newHolder = new Holder(holder);
        this.bank.holders.add(newHolder);
        System.out.println("***** HOLDER CREATED *****");
        System.out.println("**********");
        System.out.println("Name: " + newHolder.getName());
        System.out.println("Pin: " + newHolder.account.getPin());
        System.out.println("Balance: " + newHolder.account.getBalance());
        this.helloWorld();
    }

    private void holderLogin() {
        System.out.println("*****");
        System.out.println("Enter your name: ");
        System.out.println("*****");

        String holderName = this.scanner.nextLine();

        Holder isUserHolder = this.bank.findHolder(holderName);

        if (isUserHolder == null) {
            System.out.println("**********");
            System.out.println("Authentication failed");
            System.out.println("**********");
            System.exit(1);
        }

        System.out.println("**********");
        System.out.println("Enter your pin: ");
        System.out.println("**********");

        int holderPin = this.scanner.nextInt();
        this.scanner.nextLine();

        boolean isPinMatching = isUserHolder.account.getPin() == holderPin;

        if (!isPinMatching) {
            System.out.println("**********");
            System.out.println("Authentication failed");
            System.out.println("**********");
            System.exit(1);
        }

        this.holderUsages(isUserHolder);
    }
    private void holderDeposit(Holder holder) {
        System.out.println("**********");
        System.out.println("Enter deposit value: ");
        System.out.println("**********");

        double value = this.scanner.nextDouble();
        this.scanner.nextLine();

        holder.account.addBalance(value);
        holder.account.addTransaction(value, holder.getName(), "deposit");
        this.bank.addTransaction(value, holder.getName(), "deposit");

        System.out.println("**********");
        System.out.print("Deposit succeed");
        System.out.println("**********");

        this.holderUsages(holder);
    }
    private void holderWithdraw(Holder holder) {
        System.out.println("**********");
        System.out.println("Withdraw value: ");
        System.out.println("**********");

        double value = this.scanner.nextDouble();
        this.scanner.nextLine();

        holder.account.subBalance(value);
        holder.account.addTransaction(value, holder.getName(), "withdraw");
        this.bank.addTransaction(value, holder.getName(), "withdraw");

        System.out.println("**********");
        System.out.print("Withdraw succeed");
        System.out.println("**********");

        this.holderUsages(holder);
    }
    private void holderBalance(Holder holder) {
        System.out.println("**********");
        System.out.println("Account balance: ");
        System.out.print(holder.account.getBalance());
        System.out.println("**********");

        this.holderUsages(holder);
    }
    private void holderTransactions(Holder holder) {
        System.out.println("**********");
        System.out.println("********** Transactions **********");
        System.out.println("**********");

        ArrayList<Transaction> transactions = holder.account.getTransactions();

        System.out.println("Holder Name: " + holder.getName());

        for (Transaction transaction : transactions) {
            System.out.println("**********");
            System.out.println("Action: " + transaction.getAction());
            System.out.println("Value: " + transaction.getValue());
        }

        this.holderUsages(holder);
    }
    private void holderInfo(Holder holder) {
        System.out.println("**********");
        System.out.println("********** Info **********");
        System.out.println("Name: " + holder.getName());
        System.out.println("Balance: " + holder.account.getBalance());
        System.out.println("Pin: " + holder.account.getPin());
        System.out.println("**********");

        this.holderUsages(holder);
    }
    private void holderUsages(Holder holder) {
        System.out.println("**********");
        System.out.println("Enter the number of the option you want: ");
        System.out.println("1: Cash deposit");
        System.out.println("2: Withdraw money");
        System.out.println("3: Get account balance");
        System.out.println("4: Get account info");
        System.out.println("5: Get account transactions");
        System.out.println("6: Exit");
        System.out.println("**********");

        int option = this.scanner.nextInt();

        if (option == 1) {
            this.holderDeposit(holder);
        } else if (option == 2) {
            this.holderWithdraw(holder);
        } else if (option == 3) {
            this.holderBalance(holder);
        } else if (option == 4) {
            this.holderInfo(holder);
        } else if (option == 5) {
            this.holderTransactions(holder);
        } else if (option == 6) {
            System.exit(0);
        } else {
            System.out.println("Option not found");
            System.exit(1);
        }
    }
}
