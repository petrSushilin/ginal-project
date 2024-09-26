package ru.sberstart.finalproject.domain.state.card;

import ru.sberstart.finalproject.domain.enitity.card.Card;
import ru.sberstart.finalproject.domain.enitity.card.enums.CardStates;
import ru.sberstart.finalproject.global.exceptions.NotAvailableActionsException;

/**
 * Реализация поведения в состоянии "CREATED" для сущности BankAccount.
 * Данный класс является имплементацией базового поведенческого интерфейса BankAccountState.
 */
public class CardOrderedState implements CardState {
    @Override
    public void issueCard(Card card) {
        throw new NotAvailableActionsException();
    }

    @Override
    public void approveCardIssue(Card card) {
        card.setState(CardStates.APPROVED);
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
        card.setState(CardStates.EXPIRED);
    }
}
