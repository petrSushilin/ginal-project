package ru.sberstart.finalproject.domain.chain_of_responsibility.transactions;

import ru.sberstart.finalproject.application.manager.BankAccountManager;
import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;
import ru.sberstart.finalproject.global.exceptions.ForbiddenTransactionException;
import ru.sberstart.finalproject.infrastructure.repostitory.BankAccountRepositoryImpl;

import java.util.List;

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
        List<String> accountNumbers = switch (context.getTransactionTypes()) {
            case ADD_FUNDS -> List.of(context.getReceiverBankAccountNumber());
            case WITHDRAWAL -> List.of(context.getSenderBankAccountNumber());
            case TRANSFER -> List.of(context.getSenderBankAccountNumber(), context.getReceiverBankAccountNumber());
        };

        List<BankAccount> accounts = repository
                .getTransactionAccounts(accountNumbers)
                .orElseThrow(ForbiddenTransactionException::new);

        context.fillWithAccounts(accounts);
        switch (context.getTransactionTypes()) {
            case ADD_FUNDS -> {
                if (manager.reportUnavailableTransactionExecution(accounts))
                    throw new UnsupportedOperationException();
            }
            case WITHDRAWAL -> {
                if (manager.reportUnavailableTransactionExecution(accounts) ||
                        isEnoughMoneyForTransaction(context))
                    throw new UnsupportedOperationException();
            }
            case TRANSFER -> {
                if (manager.reportAllAvailableTransactionExecution(accounts) ||
                        isEnoughMoneyForTransaction(context))
                    throw new UnsupportedOperationException();
            }
        }
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
}
