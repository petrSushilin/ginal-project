package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionParamsCustomizerHandler implements TransactionHandler {
    private TransactionHandler next;

    @Override
    public void setNext(TransactionHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(TransactionContext context) {
        BigDecimal currentSenderBalance = context.getSenderBankAccount().getBalance();
        context.getSenderBankAccount().setBalance(currentSenderBalance.subtract(context.getAmount()));

        BigDecimal currentReceiverBalance = context.getReceiverBankAccount().getBalance();
        context.getReceiverBankAccount().setBalance(currentReceiverBalance.add(context.getAmount()));

        if (next != null) next.handle(context);
    }
}
