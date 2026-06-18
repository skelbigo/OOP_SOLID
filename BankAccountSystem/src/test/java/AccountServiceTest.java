import org.example.Account;
import org.example.AccountService;
import org.example.Transaction;
import org.example.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    private AccountService accountService;
    private Account testAccount;

    @BeforeEach
    void init() {
        accountService = new AccountService();
        testAccount = new Account(1, 100, "Oleksandra");
        accountService.openAccount(testAccount);
    }

    @Test
    void testAccountConstructor_shouldThrowWhenIdIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Account(0 , 1000, "Valentina"));
    }

    @Test
    void testAccountConstructor_shouldThrowWhenBalanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Account(1, -200, "Anna"));
    }

    @Test
    void testAccountConstructor_shouldThrowWhenOwnerIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Account(1, 450, ""));
    }

    @Test
    void testTransactionConstructor_shouldThrowWhenAmountIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction("id", TransactionType.DEPOSIT, -13));
    }

    @Test
    void testAccount_shouldProtectTransactionsFromMutation() {
        Transaction transaction = new Transaction("id", TransactionType.DEPOSIT, 1000);
        assertThrows(UnsupportedOperationException.class, () -> testAccount.getTransactions().add(transaction));
    }

    @Test
    void testOpenAccount_shouldAddNewAccountSuccessfully() {
        Account account = new Account(2, 500, "Alexei");
        accountService.openAccount(account);
        assertDoesNotThrow(() -> accountService.deposit(2, 100));
    }

    @Test
    void testOpenAccount_shouldThrowWhenAccountIsNull() {
        assertThrows(NullPointerException.class, () -> accountService.openAccount(null));
    }

    @Test
    void testOpenAccount_shouldThrowWhenDuplicateId() {
        assertThrows(IllegalArgumentException.class, () -> accountService.openAccount(testAccount));
    }

    @Test
    void testDeposit_shouldIncreaseBalanceAndRecordTransaction() {
        accountService.deposit(1, 900);
        assertEquals(1000, testAccount.getBalance());
        assertEquals(1, testAccount.getTransactions().size());

        Transaction recordedTx = testAccount.getTransactions().get(0);
        assertNotNull(recordedTx.getId());
        assertFalse(recordedTx.getId().isEmpty());
        assertEquals(TransactionType.DEPOSIT, recordedTx.getTransactionType());
        assertEquals(900, recordedTx.getAmount());
    }

    @Test
    void testDeposit_shouldThrowWhenAccountNotFound() {
        assertThrows(IllegalArgumentException.class, () -> accountService.deposit(2, 500));
    }

    @Test
    void testDeposit_shouldThrowWhenAmountIsZeroOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> accountService.deposit(1, -500));
    }

    @Test
    void testWithdraw_shouldDecreaseBalanceAndRecordTransaction() {
        accountService.withdraw(1, 15);
        assertEquals(85, testAccount.getBalance());
        assertEquals(1, testAccount.getTransactions().size());

        Transaction recordedTx = testAccount.getTransactions().get(0);
        assertNotNull(recordedTx.getId());
        assertFalse(recordedTx.getId().isEmpty());
        assertEquals(TransactionType.WITHDRAW, recordedTx.getTransactionType());
        assertEquals(15, recordedTx.getAmount());
    }

    @Test
    void testWithdraw_shouldThrowWhenAccountNotFound() {
        assertThrows(IllegalArgumentException.class, () -> accountService.withdraw(2, 50));
    }

    @Test
    void testWithdraw_shouldThrowWhenAmountIsZeroOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> accountService.withdraw(1, 0));
    }

    @Test
    void testWithdraw_shouldThrowWhenInsufficientFunds() {
        assertThrows(IllegalArgumentException.class, () -> accountService.withdraw(1, 200));
    }
}
