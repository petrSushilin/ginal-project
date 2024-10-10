package ru.sberstart.finalproject.domain.enitity.user.enums;

import ru.sberstart.finalproject.domain.enitity.interfaces.State;

public enum UserState implements State {
    REGISTERED,
    APPROVED,
    BLOCKED
}
