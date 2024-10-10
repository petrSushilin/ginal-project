package ru.sberstart.finalproject.domain.enitity.card.enums;

import ru.sberstart.finalproject.domain.enitity.interfaces.State;

public enum CardStates implements State {
    ORDERED,
    ACTIVE,
    APPROVED,
    BLOCKED,
    EXPIRED
}
