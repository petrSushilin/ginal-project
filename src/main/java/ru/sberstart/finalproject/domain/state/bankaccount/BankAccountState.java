package ru.sberstart.finalproject.domain.state.bankaccount;

import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;

/**
 * Данный поведенческий интерфейс банковского счета в разных состояниях является базовым.
 */
public interface BankAccountState {
    /**
     * Метод регистрации банковского счета.
     * Доступен только для новых банковских счётов.
     *
     * @param account
     */
    void registerAccount(BankAccount account);

    /**
     * Метод подтверждения банковского счета.
     * Доступен только в состоянии: CREATED, STOPPED.
     *
     * @param account
     */
    void approveAccount(BankAccount account);


    /**
     * Метод приостановки банковского счета.
     * Доступен только в состоянии: CREATED, ACTIVE.
     *
     * @param account
     */
    void suspendAccount(BankAccount account);

    /**
     * Метод закрытия банковского счета.
     * Доступен только в состоянии: CREATED, ACTIVE.
     *
     * @param account
     */
    void closeAccount(BankAccount account);

    /**
     * Возвращает готовность банковского счета для выполнения транзакции.
     * Доступен только в состоянии: ACTIVE.
     *
     * @return boolean
     */
    boolean isReadyForTransaction();
}
