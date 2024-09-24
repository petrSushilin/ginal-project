package ru.sberstart.finalproject.bankaccount.domain.states;

import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;
import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;
import ru.sberstart.finalproject.global.exceptions.NotAvailableActionsException;

/**
 * Реализация поведения в состоянии "CLOSED" для сущности BankAccount.
 * Данный класс является имплементацией базового поведенческого интерфейса BankAccountState.
 */
public class BankAccountClosedState implements BankAccountState {
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
     * Метод подтверждения лицевого счета.
     * Доступен только в состоянии: CREATED, STOPPED.
     *
     * @param account
     */
    @Override
    public void approveAccount(BankAccount account) {
        throw new NotAvailableActionsException();
    }

    /**
     * Метод приостановки лицевого счета.
     * Доступен только в состоянии: CREATED, ACTIVE.
     *
     * @param account
     */
    @Override
    public void suspendAccount(BankAccount account) {
        throw new NotAvailableActionsException();
    }

    /**
     * Метод закрытия лицевого счета.
     * Доступен только в состоянии: CREATED, ACTIVE.
     *
     * @param account
     */
    @Override
    public void closeAccount(BankAccount account) {
        throw new NotAvailableActionsException();
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
