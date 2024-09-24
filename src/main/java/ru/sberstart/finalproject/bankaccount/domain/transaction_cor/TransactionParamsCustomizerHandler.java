package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

import org.springframework.stereotype.Component;
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
        BigDecimal currentSenderBalance = context.getSenderBankAccount().getBalance();
        context.getSenderBankAccount().setBalance(currentSenderBalance.subtract(context.getAmount()));

        BigDecimal currentReceiverBalance = context.getReceiverBankAccount().getBalance();
        context.getReceiverBankAccount().setBalance(currentReceiverBalance.add(context.getAmount()));

        if (next != null) next.handle(context);
    }
}