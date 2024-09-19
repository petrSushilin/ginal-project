package ru.sberstart.finalproject.bankaccount.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sberstart.finalproject.bankaccount.api.dto.BankAccountTransactionDTO;
import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;
import ru.sberstart.finalproject.bankaccount.domain.transaction_cor.TransactionContext;
import ru.sberstart.finalproject.bankaccount.domain.transaction_cor.TransactionHandler;
import ru.sberstart.finalproject.bankaccount.domain.transaction_cor.TransactionHandlerFactory;

import java.math.BigDecimal;

@Service
public class TransactionService {
    private TransactionHandlerFactory handlerFactory;

    @Transactional
    public BankAccount executeTransaction(BankAccountTransactionDTO transactionDTO) {
        TransactionContext context = new TransactionContext(transactionDTO);
        TransactionHandler handlerChain = handlerFactory.createTransactionHandler();
        handlerChain.handle(context);
        return context.getSenderBankAccount();
    }
}
