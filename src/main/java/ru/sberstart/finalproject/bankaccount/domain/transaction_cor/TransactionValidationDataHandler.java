package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

import ru.sberstart.finalproject.bankaccount.application.manager.BankAccountManager;
import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;
import ru.sberstart.finalproject.bankaccount.infrastructure.repository.BankAccountRepositoryImpl;

import java.util.List;
import java.util.Optional;
/**
 * Класс TransactionValidationDataHandler отвечает за проверку данных транзакции.
 * Он реализует интерфейс TransactionHandler и выполняет валидацию перед выполнением транзакции.
 */
public class TransactionValidationDataHandler implements TransactionHandler {
    private TransactionHandler next;

    private final BankAccountRepositoryImpl repository;
    private final BankAccountManager manager;

    /**
     * Конструктор класса TransactionValidationDataHandler.
     *
     * @param repository Репозиторий для работы с банковскими счетами.
     * @param manager    Менеджер для управления банковскими счетами.
     */
    public TransactionValidationDataHandler(BankAccountRepositoryImpl repository, BankAccountManager manager) {
        this.repository = repository;
        this.manager = manager;
    }

    /**
     * Устанавливает следующий обработчик в цепочке.
     *
     * @param handler Следующий обработчик транзакций.
     */
    @Override
    public void setNext(TransactionHandler handler) {
        this.next = handler;
    }

    /**
     * Обрабатывает контекст транзакции, выполняя валидацию данных.
     * Если данные валидны, передает контекст следующему обработчику в цепочке.
     *
     * @param context Контекст транзакции.
     * @throws UnsupportedOperationException если данные транзакции не валидны.
     */
    @Override
    public void handle(TransactionContext context) {
        Optional<List<BankAccount>> accountRecords = repository.getTransactionAccounts(List.of(context.getSenderBankAccountNumber(),
                context.getReceiverBankAccountNumber()));
        List<BankAccount> accounts = accountRecords.orElseThrow();

        context.fillWithAccounts(accounts);

        if (manager.reportNotAvailableTransactionExecution(accounts) ||
                isEnoughMoneyForTransaction(context) ||
                isIncorrectNumberOfTransactionParticipants(accounts))
            throw new UnsupportedOperationException();

        if (next != null) next.handle(context);
    }

    /**
     * Проверяет, достаточно ли средств на счете отправителя для выполнения транзакции.
     *
     * @param context Контекст транзакции.
     * @return true, если средств достаточно, иначе false.
     */
    private boolean isEnoughMoneyForTransaction(TransactionContext context) {
        return context.getSenderBankAccount().getBalance().compareTo(context.getAmount()) >= 0;
    }

    /**
     * Проверяет, корректное ли количество участников транзакции.
     *
     * @param setTransactionParticipants Список участников транзакции.
     * @return true, если количество участников некорректно, иначе false.
     */
    private boolean isIncorrectNumberOfTransactionParticipants(List<?> setTransactionParticipants) {
        return setTransactionParticipants.size() != 2;
    }
}
