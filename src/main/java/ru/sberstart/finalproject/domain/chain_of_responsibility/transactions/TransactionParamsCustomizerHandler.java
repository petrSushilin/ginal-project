package ru.sberstart.finalproject.domain.chain_of_responsibility.transactions;

import org.springframework.stereotype.Component;
import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.TransactionTypes;

import java.math.BigDecimal;

/**
 * Класс TransactionParamsCustomizerHandler реализует интерфейс TransactionHandler и отвечает за
 * обновление балансов банковских счетов отправителя и получателя в контексте транзакции.
 */
@Component
public class TransactionParamsCustomizerHandler implements TransactionHandler {
    private TransactionHandler next;

    /**
     * Устанавливает следующий обработчик в цепочке обработки транзакций.
     *
     * @param handler следующий обработчик транзакций.
     */
    @Override
    public void setNext(TransactionHandler handler) {
        this.next = handler;
    }

    /**
     * Обрабатывает контекст транзакции, обновляя балансы банковских счетов отправителя и получателя.
     * Если установлен следующий обработчик, передает ему контекст для дальнейшей обработки.
     *
     * @param context контекст транзакции, содержащий информацию о счетах и сумме транзакции.
     */
    @Override
    public void handle(TransactionContext context) {
        switch (context.getTransactionTypes()) {
            case TRANSFER, WITHDRAWAL -> {
                BigDecimal currentSenderBalance = context.getSenderBankAccount().getBalance();
                context.getSenderBankAccount().setBalance(currentSenderBalance.subtract(context.getAmount()));
            }
        }

        switch (context.getTransactionTypes()) {
            case TRANSFER, ADD_FUNDS -> {
                BigDecimal currentReceiverBalance = context.getReceiverBankAccount().getBalance();
                context.getReceiverBankAccount().setBalance(currentReceiverBalance.add(context.getAmount()));
            }
        }

        if (next != null) next.handle(context);
    }
}