package ru.sberstart.finalproject.bankaccount.domain.states;

import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;
import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;

/**
 * Реализация поведения в состоянии "CREATED" для сущности BankAccount.
 * Данный класс является имплементацией базового поведенческого интерфейса BankAccountState.
 */
public class BankAccountCreatedState implements BankAccountState {
    /**
     * Метод регистрации банковского счета.
     * Доступен только для новых банковских счётов.
     *
     * @param account
     */
    public void registerAccount(BankAccount account) {
        account.setState(BankAccountStates.CREATED);
    }

    /**
     * Метод подтверждения банковского счета.
     * Доступен только в состоянии: CREATED, STOPPED.
     *
     * @param account
     */
    @Override
    public void approveAccount(BankAccount account) {
        account.setState(BankAccountStates.ACTIVE);
    }

    /**
     * Метод приостановки банковского счета.
     * Доступен только в состоянии: CREATED, ACTIVE.
     *
     * @param account
     */
    @Override
    public void suspendAccount(BankAccount account) {
        account.setState(BankAccountStates.STOPPED);
    }

    /**
     * Метод закрытия банковского счета.
     * Доступен только в состоянии: CREATED, ACTIVE.
     *
     * @param account
     */
    @Override
    public void closeAccount(BankAccount account) {
        account.setState(BankAccountStates.STOPPED);
    }

    /**
     * Возвращает готовность банковского счета для выполнения транзакции.
     * Доступен только в состоянии: ACTIVE.
     *
     * @return boolean
     */
    @Override
    public boolean isReadyForTransaction() {
        return false;
    }
}
