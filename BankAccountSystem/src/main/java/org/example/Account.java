package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Account {
    private final long id;
    private long balance;
    private final String owner;
    private final List<Transaction> transactions;

    public Account(long id, long balance, String owner) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        if (owner == null || owner.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner cannot be null or empty");
        }
        this.id = id;
        this.balance = balance;
        this.owner = owner;
        this.transactions = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("The deposit amount must be positive");
        }
        this.balance += amount;
        String txId = UUID.randomUUID().toString();
        this.transactions.add(new Transaction(txId, TransactionType.DEPOSIT, amount));
    }

    public void withdraw(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("The withdrawal amount must be positive");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("There are insufficient funds in the account");
        }
        this.balance -= amount;
        String txId = UUID.randomUUID().toString();
        this.transactions.add(new Transaction(txId, TransactionType.WITHDRAW, amount));
    }
}
