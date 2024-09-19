package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

public interface TransactionHandler {
    void setNext(TransactionHandler handler);
    void handle(TransactionContext context);
}
