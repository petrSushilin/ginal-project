package ru.sberstart.finalproject.card.domain.states;

import ru.sberstart.finalproject.card.domain.entity.Card;
import ru.sberstart.finalproject.card.domain.entity.enums.CardStates;
import ru.sberstart.finalproject.global.exceptions.NotAvailableActionsException;

/**
 * Реализация поведения в состоянии "STOPPED" для сущности BankAccount.
 * Данный класс является имплементацией базового поведенческого интерфейса BankAccountState.
 */
public class CardExpiredState implements CardState {
    @Override
    public void issueCard(Card card) {
        card.setState(CardStates.ORDERED);
    }

    @Override
    public void approveCardIssue(Card card) {
        throw new NotAvailableActionsException();
    }

    @Override
    public void activateCard(Card card) {
        throw new NotAvailableActionsException();
    }

    @Override
    public void suspendCard(Card card) {
        throw new NotAvailableActionsException();
    }

    @Override
    public void blockExpireCard(Card card) {
        throw new NotAvailableActionsException();
    }
}
