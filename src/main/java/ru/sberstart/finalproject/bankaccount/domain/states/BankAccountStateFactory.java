package ru.sberstart.finalproject.bankaccount.domain.states;

import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;

public class BankAccountStateFactory {
    public static BankAccountState getState(BankAccount account) {
        return switch (account.getState()) {
            case CREATED -> new BankAccountCreatedState();
            case ACTIVE -> new BankAccountActiveState();
            case STOPPED -> new BankAccountStoppedState();
            case CLOSED -> new BankAccountClosedState();
        };
    }
}
