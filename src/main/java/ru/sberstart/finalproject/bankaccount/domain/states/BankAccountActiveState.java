package ru.sberstart.finalproject.bankaccount.domain.states;

import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;
import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;
import ru.sberstart.finalproject.global.exceptions.NotAvailableActionsException;

/**
 * Реализация поведения в состоянии "ACTIVE" для сущности BankAccount.
 * Данный класс является имплементацией базового поведенческого интерфейса BankAccountState.
 */
public class BankAccountActiveState implements BankAccountState {
    /**
     * Метод регистрации банковского счета.
     * Доступен только для новых банковских счётов.
     *
     * @param account
     */
    public void registerAccount(BankAccount account) {
        throw new NotAvailableActionsException();
    }
    /**
     * Метод подтверждения банковского счета.
     * Доступен только в состоянии: CREATED, STOPPED.
     *
     * @param account
     */
    @Override
    public void approveAccount(BankAccount account) {
        throw new NotAvailableActionsException();
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
        account.setState(BankAccountStates.CLOSED);
    }

    /**
     * Возвращает готовность банковского счета для выполнения транзакции.
     * Доступен только в состоянии: ACTIVE.
     *
     * @return boolean
     */
    @Override
    public boolean isReadyForTransaction() {
        return true;
    }
}
