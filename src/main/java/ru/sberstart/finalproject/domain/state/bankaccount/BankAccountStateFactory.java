package ru.sberstart.finalproject.domain.state.bankaccount;

import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;

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
