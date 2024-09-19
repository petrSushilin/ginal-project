package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

import org.springframework.stereotype.Component;
import ru.sberstart.finalproject.bankaccount.application.manager.BankAccountManager;
import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;
import ru.sberstart.finalproject.bankaccount.infrastructure.repository.BankAccountRepositoryImpl;

import java.util.List;

@Component
public class TransactionValidationDataHandler implements TransactionHandler {
    private TransactionHandler next;

    private final BankAccountRepositoryImpl repository;
    private final BankAccountManager manager;

    public TransactionValidationDataHandler(BankAccountRepositoryImpl repository) {
        this.repository = repository;
        this.manager = new BankAccountManager();
    }

    @Override
    public void setNext(TransactionHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(TransactionContext context) {
        List<BankAccount> accounts = repository.getTransactionAccounts(List.of(context.getSenderBankAccountNumber(),
                                                                                context.getReceiverBankAccountNumber()));

        context.fillWithAccounts(accounts);

        if (manager.reportNotAvailableTransactionExecution(accounts) ||
                isEnoughMoneyForTransaction(context) ||
                isIncorrectNumberOfTransactionParticipants(accounts))
            throw new UnsupportedOperationException();

        if (next != null) next.handle(context);
    }

    private boolean isEnoughMoneyForTransaction(TransactionContext context) {
        return context.getSenderBankAccount().getBalance().compareTo(context.getAmount()) >= 0;
    }

    private boolean isIncorrectNumberOfTransactionParticipants(List<?> setTransactionParticipants) {
        return setTransactionParticipants.size() != 2;
    }
}
