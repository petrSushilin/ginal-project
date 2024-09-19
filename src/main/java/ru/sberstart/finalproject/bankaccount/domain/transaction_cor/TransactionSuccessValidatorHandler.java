package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

import org.springframework.stereotype.Component;

@Component
public class TransactionSuccessValidatorHandler implements TransactionHandler {
    private TransactionHandler next;

    @Override
    public void setNext(TransactionHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(TransactionContext context) {
        if (isIncorrectResult(context)) throw new Error();

        if (next != null) next.handle(context);
    }

    private boolean isIncorrectResult(TransactionContext context) {
        boolean decreaseSenderBalanceSuccess = context.getSenderBankAccount().getBalance()
                                .compareTo(context.getStartSenderAccountBalance().subtract(context.getAmount())) == 0;

        boolean increaseReceiverBalanceSuccess = context.getReceiverBankAccount().getBalance()
                                .compareTo(context.getStartReceiverAccountBalance().subtract(context.getAmount())) == 0;

        return decreaseSenderBalanceSuccess && increaseReceiverBalanceSuccess;
    }
}
