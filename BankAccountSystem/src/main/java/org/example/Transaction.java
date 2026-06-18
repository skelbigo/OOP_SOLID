package org.example;

public class Transaction {
    private final String id;
    private final TransactionType transactionType;
    private final long amount;

    public Transaction(String id, TransactionType transactionType, long amount) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be nul or empty");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Balance must be positive");
        }
        this.id = id;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public long getAmount() {
        return amount;
    }
}
