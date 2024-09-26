package ru.sberstart.finalproject.infrastructure.repostitory.interfaces;

import ru.sberstart.finalproject.api.dto.bankaccount.inner.BankAccountFullInformationDTO;
import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    Optional<BankAccount> create(BankAccount bankAccount);

    /**
     * Метод получения сущности банковского счета в БД.
     *
     * @param number
     * @return BankAccount
     */
    Optional<BankAccount> getServiceInfoByNumber(String number);

    Optional<BankAccountFullInformationDTO> getByNumber(String number);

    /**
     * Метод получения записей банковских счетов из БД, участвующих в процессе транзакции.
     *
     * @param parsedAccountNumbers
     * @return
     */
    Optional<List<BankAccount>> getTransactionAccounts(List<String> parsedAccountNumbers);

    /**
     * Метод обновлнеия состояния банковского счета в БД.
     *
     * @param account
     * @return BankAccount
     */
    Optional<BankAccount> updateState(BankAccount account);

    /**
     * Метод исполнения денежной транзакции.
     *
     * @param transactionAccountRecords
     * @return List of BankAccount
     */
    Optional<List<BankAccount>> provideTransaction(List<BankAccount> transactionAccountRecords) throws IOException;
}
