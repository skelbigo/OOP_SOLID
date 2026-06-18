package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountService {
    private final Map<Long, Account> accounts = new HashMap<>();

    public void openAccount(Account account) {
        Objects.requireNonNull(account, "Account can not be null");
        if (accounts.putIfAbsent(account.getId(), account) != null) {
            throw new IllegalArgumentException("Account with id " + account.getId() + " exists");
        };
    }

    public void deposit(long idAccount, long reloadAmount) {
        Account account = findAccountOrThrow(idAccount);
        account.deposit(reloadAmount);
    }

    public void withdraw(long idAccount, long withdrawalAmount) {
        Account account = findAccountOrThrow(idAccount);
        account.withdraw(withdrawalAmount);
    }

    private Account findAccountOrThrow(long idAccount) {
        Account account = accounts.get(idAccount);
        if (account == null) {
            throw new IllegalArgumentException("Account with id " + idAccount + " not exists");
        }
        return account;
    }
}
