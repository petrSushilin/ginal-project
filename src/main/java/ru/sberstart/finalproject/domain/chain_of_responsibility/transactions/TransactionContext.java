package ru.sberstart.finalproject.domain.chain_of_responsibility.transactions;

import ru.sberstart.finalproject.api.dto.bankaccount.request.BankAccountTransferTransactionRequestDTO;
import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;
import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.TransactionTypes;

import java.math.BigDecimal;
import java.util.List;
/**
 * Класс TransactionContext представляет контекст транзакции между двумя банковскими счетами.
 * Он содержит информацию о номерах счетов отправителя и получателя, начальных балансах этих счетов,
 * а также о сумме транзакции.
 */
public class TransactionContext {
    private final String senderBankAccountNumber;
    private BigDecimal startSenderAccountBalance;
    private BankAccount senderBankAccount;
    private final String receiverBankAccountNumber;
    private BigDecimal startReceiverAccountBalance;
    private BankAccount receiverBankAccount;
    private final BigDecimal amount;
    private final TransactionTypes transactionTypes;

    /**
     * Конструктор класса TransactionContext.
     * Инициализирует контекст транзакции на основе данных из DTO.
     *
     * @param transactionDTO DTO с информацией о транзакции.
     */
    public TransactionContext(BankAccountTransferTransactionRequestDTO transactionDTO) {
        this.senderBankAccountNumber = transactionDTO.senderBankAccountNumber();
        this.receiverBankAccountNumber = transactionDTO.receiverBankAccountNumber();
        this.amount = transactionDTO.amount();
        if (transactionDTO.senderBankAccountNumber().isBlank()) {
            transactionTypes = TransactionTypes.ADD_FUNDS;
        } else if (transactionDTO.receiverBankAccountNumber().isBlank()) {
            transactionTypes = TransactionTypes.WITHDRAWAL;
        } else {
            transactionTypes = TransactionTypes.TRANSFER;
        }
    }

    /**
     * Возвращает номер банковского счета отправителя.
     *
     * @return номер банковского счета отправителя.
     */
    public String getSenderBankAccountNumber() {
        return senderBankAccountNumber;
    }

    /**
     * Возвращает начальный баланс счета отправителя.
     *
     * @return начальный баланс счета отправителя.
     */
    public BigDecimal getStartSenderAccountBalance() {
        return startSenderAccountBalance;
    }

    /**
     * Устанавливает начальный баланс счета отправителя.
     *
     * @param startSenderAccountBalance начальный баланс счета отправителя.
     */
    private void setStartSenderAccountBalance(BigDecimal startSenderAccountBalance) {
        this.startSenderAccountBalance = startSenderAccountBalance;
    }

    /**
     * Возвращает объект банковского счета отправителя.
     *
     * @return объект банковского счета отправителя.
     */
    public BankAccount getSenderBankAccount() {
        return senderBankAccount;
    }

    /**
     * Устанавливает объект банковского счета отправителя.
     *
     * @param senderBankAccount объект банковского счета отправителя.
     */
    private void setSenderBankAccount(BankAccount senderBankAccount) {
        this.senderBankAccount = senderBankAccount;
    }

    /**
     * Возвращает номер банковского счета получателя.
     *
     * @return номер банковского счета получателя.
     */
    public String getReceiverBankAccountNumber() {
        return receiverBankAccountNumber;
    }

    /**
     * Возвращает начальный баланс счета получателя.
     *
     * @return начальный баланс счета получателя.
     */
    public BigDecimal getStartReceiverAccountBalance() {
        return startReceiverAccountBalance;
    }

    /**
     * Устанавливает начальный баланс счета получателя.
     *
     * @param startReceiverAccountBalance начальный баланс счета получателя.
     */
    private void setStartReceiverAccountBalance(BigDecimal startReceiverAccountBalance) {
        this.startReceiverAccountBalance = startReceiverAccountBalance;
    }

    /**
     * Возвращает объект банковского счета получателя.
     *
     * @return объект банковского счета получателя.
     */
    public BankAccount getReceiverBankAccount() {
        return receiverBankAccount;
    }

    /**
     * Устанавливает объект банковского счета получателя.
     *
     * @param receiverBankAccount объект банковского счета получателя.
     */
    private void setReceiverBankAccount(BankAccount receiverBankAccount) {
        this.receiverBankAccount = receiverBankAccount;
    }

    /**
     * Возвращает сумму транзакции.
     *
     * @return сумма транзакции.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionTypes getTransactionTypes() {
        return transactionTypes;
    }

    /**
     * Заполняет контекст транзакции объектами банковских счетов на основе списка счетов.
     * Устанавливает начальные балансы счетов отправителя и получателя, если они еще не установлены.
     *
     * @param accounts список объектов банковских счетов.
     */
    public void fillWithAccounts(List<BankAccount> accounts) {
        if (!this.getSenderBankAccountNumber().isEmpty()) {
            accounts.stream()
                    .filter(account -> account.getNumber().equals(this.getSenderBankAccountNumber()))
                    .findFirst()
                    .ifPresent(account -> {
                        this.setSenderBankAccount(account);
                        if (this.getStartSenderAccountBalance() == null)
                            this.setStartSenderAccountBalance(account.getBalance());
                    });
        }
        if (!this.getReceiverBankAccountNumber().isEmpty()) {
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
}
