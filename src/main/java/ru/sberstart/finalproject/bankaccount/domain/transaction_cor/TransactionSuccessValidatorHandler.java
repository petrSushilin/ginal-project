package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

/**
 * Класс TransactionSuccessValidatorHandler представляет обработчик, который проверяет успешность транзакции.
 * Он является частью цепочки обязанностей и проверяет, корректно ли были изменены балансы отправителя и получателя.
 */
public class TransactionSuccessValidatorHandler implements TransactionHandler {
    private TransactionHandler next;

    /**
     * Устанавливает следующий обработчик в цепочке обязанностей.
     *
     * @param handler следующий обработчик.
     */
    @Override
    public void setNext(TransactionHandler handler) {
        this.next = handler;
    }

    /**
     * Обрабатывает транзакцию в заданном контексте.
     * Проверяет, корректно ли были изменены балансы отправителя и получателя.
     * Если проверка не пройдена, выбрасывает ошибку.
     * Если проверка пройдена, передает обработку следующему обработчику в цепочке.
     *
     * @param context контекст транзакции, содержащий информацию о транзакции.
     */
    @Override
    public void handle(TransactionContext context) {
        if (isIncorrectResult(context)) throw new Error();

        if (next != null) next.handle(context);
    }

    /**
     * Проверяет, корректно ли были изменены балансы отправителя и получателя.
     *
     * @param context контекст транзакции, содержащий информацию о транзакции.
     * @return true, если балансы изменены корректно, иначе false.
     */
    private boolean isIncorrectResult(TransactionContext context) {
        boolean decreaseSenderBalanceSuccess = context.getSenderBankAccount().getBalance()
                .compareTo(context.getStartSenderAccountBalance().subtract(context.getAmount())) == 0;

        boolean increaseReceiverBalanceSuccess = context.getReceiverBankAccount().getBalance()
                .compareTo(context.getStartReceiverAccountBalance().subtract(context.getAmount())) == 0;

        return decreaseSenderBalanceSuccess && increaseReceiverBalanceSuccess;
    }
}
