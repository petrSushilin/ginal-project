package ru.sberstart.finalproject.bankaccount.domain.states;

import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;
import ru.sberstart.finalproject.card.application.service.CardService;
import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;
import ru.sberstart.finalproject.global.exceptions.NoValidateActionsException;

import java.math.BigDecimal;

/**
 * Реализация поведения в состоянии "ACTIVE" для сущности BankAccount.
 * Данный класс является имплементацией базового поведенческого интерфейса BankAccountState.
 */
public class BankAccountActiveState implements BankAccountState {
    /**
     * Метод выпуска карты для существующего банковского счета.
     * Доступен только в состоянии: ACTIVE.
     *
     * Для активного банковского счета может быть выпущенно неограниченное количество карт.
     * Для выпуска карты счет должен быть положительным (не менее 0).
     *
     * @param account
     * @param service
     */
    @Override
    public void createCard(BankAccount account, CardService service) {
        if (account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new NoValidateActionsException();
        }
        service.createCard(account);
    }

    /**
     * Метод подтверждения банковского счета.
     * Доступен только в состоянии: CREATED, STOPPED.
     *
     * @param account
     */
    @Override
    public void approveAccount(BankAccount account) {
        throw new NoValidateActionsException();
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
