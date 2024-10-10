package ru.sberstart.finalproject.domain.enitity.bankaccount.enums;

import ru.sberstart.finalproject.domain.enitity.interfaces.State;

public enum BankAccountStates implements State {
    CREATED,
    ACTIVE,
    STOPPED,
    CLOSED
}
