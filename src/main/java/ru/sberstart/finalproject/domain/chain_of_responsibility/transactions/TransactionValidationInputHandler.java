package ru.sberstart.finalproject.domain.chain_of_responsibility.transactions;

import ru.sberstart.finalproject.application.manager.BankAccountManager;
import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.TransactionTypes;

import java.math.BigDecimal;
import java.util.Optional;

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
        boolean a = Optional.ofNullable(context.getSenderBankAccountNumber())
                .filter(this::isMismatchNumberPattern)
                .isPresent();
        boolean b = Optional.ofNullable(context.getReceiverBankAccountNumber())
                .filter(this::isMismatchNumberPattern)
                .isPresent();
        boolean c = isIncorrectAmountTransaction(context.getAmount());
        if ((a || b) && c) {
            // эксепшен будет выброшен, только в том случае, если будет больше меньше 1
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
    private boolean isMismatchNumberPattern(String accountNumber) {
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