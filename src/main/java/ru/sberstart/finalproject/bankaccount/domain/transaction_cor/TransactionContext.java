package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountTransactionDTO;
import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;

import java.math.BigDecimal;
import java.util.List;

public class TransactionContext {
    private final String senderBankAccountNumber;
    private BigDecimal startSenderAccountBalance;
    private BankAccount senderBankAccount;
    private final String receiverBankAccountNumber;
    private BigDecimal startReceiverAccountBalance;
    private BankAccount receiverBankAccount;
    private final BigDecimal amount;

    public TransactionContext(BankAccountTransactionDTO transactionDTO) {
        this.senderBankAccountNumber = transactionDTO.senderBankAccountNumber();
        this.receiverBankAccountNumber = transactionDTO.receiverBankAccountNumber();
        this.amount = transactionDTO.amount();
    }

    public String getSenderBankAccountNumber() {
        return senderBankAccountNumber;
    }

    public BigDecimal getStartSenderAccountBalance() {
        return startSenderAccountBalance;
    }

    private void setStartSenderAccountBalance(BigDecimal startSenderAccountBalance) {
        this.startSenderAccountBalance = startSenderAccountBalance;
    }

    public BankAccount getSenderBankAccount() {
        return senderBankAccount;
    }

    private void setSenderBankAccount(BankAccount senderBankAccount) {
        this.senderBankAccount = senderBankAccount;
    }

    public String getReceiverBankAccountNumber() {
        return receiverBankAccountNumber;
    }

    public BigDecimal getStartReceiverAccountBalance() {
        return startReceiverAccountBalance;
    }

    private void setStartReceiverAccountBalance(BigDecimal startReceiverAccountBalance) {
        this.startReceiverAccountBalance = startReceiverAccountBalance;
    }

    public BankAccount getReceiverBankAccount() {
        return receiverBankAccount;
    }

    private void setReceiverBankAccount(BankAccount receiverBankAccount) {
        this.receiverBankAccount = receiverBankAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Метод для заполнения полей объектов BankAccount, участвующих в транзакции.
     *
     * @param accounts
     */
    public void fillWithAccounts(List<BankAccount> accounts) {
        accounts.stream()
                .filter(account -> account.getNumber().equals(this.getSenderBankAccountNumber()))
                .findFirst()
                .ifPresent(account -> {
                    this.setSenderBankAccount(account);
                    if (this.getStartSenderAccountBalance() == null)
                        this.setStartSenderAccountBalance(account.getBalance());
                });
        accounts.stream()
                .filter(account -> account.getNumber().equals(this.getReceiverBankAccountNumber()))
                .findFirst()
                .ifPresent(account -> {
                    this.setReceiverBankAccount(account);
                    if (this.getStartReceiverAccountBalance() == null)
                        this.setStartReceiverAccountBalance(account.getBalance());
                });
    }
}
