package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

/**
 * Интерфейс TransactionHandler представляет обработчик транзакций в цепочке обязанностей.
 * Каждый обработчик может передавать обработку следующему обработчику в цепочке.
 */
public interface TransactionHandler {

    /**
     * Устанавливает следующий обработчик в цепочке обязанностей.
     *
     * @param handler следующий обработчик.
     */
    void setNext(TransactionHandler handler);

    /**
     * Обрабатывает транзакцию в заданном контексте.
     * Реализация метода должна содержать логику обработки транзакции.
     *
     * @param context контекст транзакции, содержащий информацию о транзакции.
     */
    void handle(TransactionContext context);
}
