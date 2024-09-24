package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

/**
 * Класс TransactionHandlerFactory отвечает за создание цепочки обработчиков транзакций.
 * Он инициализирует и связывает обработчики в определенном порядке, обеспечивая последовательную обработку транзакций.
 */
public class TransactionHandlerFactory {
    private final TransactionValidationInputHandler transactionValidationInputHandler;
    private final TransactionValidationDataHandler transactionValidationDataHandler;
    private final TransactionParamsCustomizerHandler transactionParamsCustomizerHandler;
    private final TransactionProviderHandler transactionProviderHandler;
    private final TransactionSuccessValidatorHandler transactionSuccessValidatorHandler;

    /**
     * Конструктор класса TransactionHandlerFactory.
     * Инициализирует все необходимые обработчики транзакций.
     *
     * @param transactionValidationInputHandler Обработчик для проверки валидности входных данных транзакции.
     * @param transactionValidationDataHandler Обработчик для проверки данных транзакции.
     * @param transactionParamsCustomizerHandler Обработчик для обновления балансов банковских счетов.
     * @param transactionProviderHandler Обработчик для выполнения транзакций между банковскими счетами.
     * @param transactionSuccessValidatorHandler Обработчик для проверки успешности транзакции.
     */
    public TransactionHandlerFactory(TransactionValidationInputHandler transactionValidationInputHandler,
                                     TransactionValidationDataHandler transactionValidationDataHandler,
                                     TransactionParamsCustomizerHandler transactionParamsCustomizerHandler,
                                     TransactionProviderHandler transactionProviderHandler,
                                     TransactionSuccessValidatorHandler transactionSuccessValidatorHandler) {
        this.transactionValidationInputHandler = transactionValidationInputHandler;
        this.transactionValidationDataHandler = transactionValidationDataHandler;
        this.transactionParamsCustomizerHandler = transactionParamsCustomizerHandler;
        this.transactionProviderHandler = transactionProviderHandler;
        this.transactionSuccessValidatorHandler = transactionSuccessValidatorHandler;
    }

    /**
     * Создает и возвращает цепочку обработчиков транзакций.
     * Устанавливает последовательность обработчиков для обработки транзакций.
     *
     * @return Первый обработчик в цепочке обработки транзакций.
     */
    public TransactionHandler createTransactionHandler() {
        transactionValidationInputHandler.setNext(transactionValidationDataHandler);
        transactionValidationDataHandler.setNext(transactionParamsCustomizerHandler);
        transactionParamsCustomizerHandler.setNext(transactionProviderHandler);
        transactionProviderHandler.setNext(transactionSuccessValidatorHandler);
        return transactionValidationInputHandler;
    }
}