package ru.sberstart.finalproject.card.domain.states;

import ru.sberstart.finalproject.card.domain.entity.Card;
import ru.sberstart.finalproject.card.domain.entity.enums.CardStates;
import ru.sberstart.finalproject.global.exceptions.NotAvailableActionsException;

public class CardApproveState implements CardState {
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
        card.setState(CardStates.ACTIVE);
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
