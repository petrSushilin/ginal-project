package ru.sberstart.finalproject.bankaccount.infrastructure.repository;

import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;

import java.io.IOException;
import java.util.List;

/**
 * Данный поведенческий интерфейс слоя репозиторий банковских счетов является базовым.
 */
public interface BankAccountRepository {
    /**
     * Метод создания сущности банковского счета в БД.
     *
     * @param bankAccount
     * @return BankAccount
     */
    BankAccount create(BankAccount bankAccount);

    /**
     * Метод получения сущности банковского счета в БД.
     *
     * @param number
     * @return BankAccount
     */
    BankAccount getByNumber(String number);

    /**
     * Метод получения записей банковских счетов из БД, участвующих в процессе транзакции.
     *
     * @param parsedAccountNumbers
     * @return
     */
    List<BankAccount> getTransactionAccounts(List<String> parsedAccountNumbers);

    /**
     * Метод обновлнеия состояния банковского счета в БД.
     *
     * @param account
     * @return BankAccount
     */
    BankAccount updateState(BankAccount account);

    /**
     * Метод исполнения денежной транзакции.
     *
     * @param transactionAccountRecords
     * @return List of BankAccount
     */
    List<BankAccount> provideTransaction(List<BankAccount> transactionAccountRecords) throws IOException;
}
