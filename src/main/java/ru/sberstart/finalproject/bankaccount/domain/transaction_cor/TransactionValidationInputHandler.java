package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Этот элемент цепочки отвечает за проверку валидности входных данных на обработку транзакции.
 */
@Component
public class TransactionValidationInputHandler implements TransactionHandler {
    private TransactionHandler next;

    private static final Pattern PATTERN = Pattern.compile("^\\d{8}-[a-fA-F0-9]{32}$");

    private static final BigDecimal MIN_AMOUNT = BigDecimal.ZERO;

    @Override
    public void setNext(TransactionHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(TransactionContext context) {
        if (isNotMatchNumberPattern(context.getSenderBankAccountNumber()) ||
                isNotMatchNumberPattern(context.getReceiverBankAccountNumber()) ||
                    isIncorrectAmountTransaction(context.getAmount())
        ) throw new UnsupportedOperationException();

        if (next != null) next.handle(context);
    }

    /**
     * Метод проверки валидности паттерна номера.
     *
     * @param accountNumber
     * @return boolean
     */
    private boolean isNotMatchNumberPattern(String accountNumber) {
        return !PATTERN.asMatchPredicate().test(accountNumber);
    }

    /**
     * Метод проверки валидности суммы
     *
     * @param amount
     * @return boolean
     */
    private boolean isIncorrectAmountTransaction(BigDecimal amount) {
        return amount.compareTo(MIN_AMOUNT) >= 0;
    }
}
