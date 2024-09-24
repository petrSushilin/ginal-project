package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

import ru.sberstart.finalproject.bankaccount.application.manager.BankAccountManager;

import java.math.BigDecimal;

/**
 * Этот элемент цепочки отвечает за проверку валидности входных данных на обработку транзакции.
 */
public class TransactionValidationInputHandler implements TransactionHandler {
    private TransactionHandler next;
    private final BankAccountManager manager;

    /**
     * Конструктор класса TransactionValidationInputHandler.
     * Инициализирует менеджер банковских счетов.
     *
     * @param manager Менеджер банковских счетов.
     */
    public TransactionValidationInputHandler(BankAccountManager manager) {
        this.manager = manager;
    }

    private static final BigDecimal MIN_AMOUNT = BigDecimal.ZERO;

    /**
     * Устанавливает следующий обработчик в цепочке.
     *
     * @param handler Следующий обработчик.
     */
    @Override
    public void setNext(TransactionHandler handler) {
        this.next = handler;
    }

    /**
     * Обрабатывает контекст транзакции, проверяя валидность входных данных.
     * Если данные не валидны, выбрасывает UnsupportedOperationException.
     * Если данные валидны, передает контекст следующему обработчику в цепочке.
     *
     * @param context Контекст транзакции.
     */
    @Override
    public void handle(TransactionContext context) {
        if (isNotMatchNumberPattern(context.getSenderBankAccountNumber()) ||
                isNotMatchNumberPattern(context.getReceiverBankAccountNumber()) ||
                isIncorrectAmountTransaction(context.getAmount())) {
            throw new UnsupportedOperationException();
        }

        if (next != null) next.handle(context);
    }

    /**
     * Метод проверки валидности паттерна номера банковского счета.
     *
     * @param accountNumber Номер банковского счета.
     * @return true, если номер не соответствует шаблону, иначе false.
     */
    private boolean isNotMatchNumberPattern(String accountNumber) {
        return manager.isInvalidateNumberPattern(accountNumber);
    }

    /**
     * Метод проверки валидности суммы транзакции.
     *
     * @param amount Сумма транзакции.
     * @return true, если сумма меньше минимально допустимой, иначе false.
     */
    private boolean isIncorrectAmountTransaction(BigDecimal amount) {
        return amount.compareTo(MIN_AMOUNT) <= 0;
    }
}