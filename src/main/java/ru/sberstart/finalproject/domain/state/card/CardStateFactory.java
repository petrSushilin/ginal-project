package ru.sberstart.finalproject.domain.state.card;

import ru.sberstart.finalproject.domain.enitity.card.Card;

public class CardStateFactory {
    public static CardState getState(Card card) {
        return switch (card.getState()) {
            case ORDERED -> new CardOrderedState();
            case ACTIVE -> new CardActiveState();
            case APPROVED -> new CardApproveState();
            case BLOCKED -> new CardBlockedState();
            case EXPIRED -> new CardExpiredState();
        };
    }
}
