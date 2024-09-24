package ru.sberstart.finalproject.card.domain.states;

import ru.sberstart.finalproject.card.domain.entity.Card;
import ru.sberstart.finalproject.card.domain.entity.enums.CardStates;
import ru.sberstart.finalproject.global.exceptions.NotAvailableActionsException;

/**
 * Реализация поведения в состоянии "ACTIVE" для сущности Card.
 * Данный класс является имплементацией базового поведенческого интерфейса CardState.
 */
public class CardActiveState implements CardState {
    @Override
    public void issueCard(Card card) {
        throw new NotAvailableActionsException();
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
        card.setState(CardStates.BLOCKED);
    }

    @Override
    public void blockExpireCard(Card card) {
        card.setState(CardStates.EXPIRED);
    }
}
