package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;
import ru.sberstart.finalproject.bankaccount.infrastructure.repository.BankAccountRepositoryImpl;
import ru.sberstart.finalproject.global.exceptions.ForbiddenTransactionException;

import java.io.IOException;
import java.util.List;

@Component
public class TransactionProviderHandler implements TransactionHandler {
    private TransactionHandler next;

    private final BankAccountRepositoryImpl repository;

    public TransactionProviderHandler(BankAccountRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public void setNext(TransactionHandler handler) {
        this.next = handler;
    }

    @Transactional(rollbackFor = ForbiddenTransactionException.class)
    @Override
    public void handle(TransactionContext context) {
        try {
            List<BankAccount> accountsAfterTransaction = repository
                        .provideTransaction(List.of(context.getSenderBankAccount(), context.getReceiverBankAccount()));
            context.fillWithAccounts(accountsAfterTransaction);
        } catch (IOException e) {
            throw new ForbiddenTransactionException();
        }

        if (next != null) next.handle(context);
    }
}
