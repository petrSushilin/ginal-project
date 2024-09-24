package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;
import ru.sberstart.finalproject.bankaccount.infrastructure.repository.BankAccountRepositoryImpl;
import ru.sberstart.finalproject.global.exceptions.ForbiddenTransactionException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Класс TransactionProviderHandler реализует интерфейс TransactionHandler и отвечает за выполнение транзакций
 * между банковскими счетами. Он использует репозиторий BankAccountRepositoryImpl для взаимодействия с базой данных.
 */
@Component
public class TransactionProviderHandler implements TransactionHandler {
    private TransactionHandler next;

    private final BankAccountRepositoryImpl repository;

    /**
     * Конструктор класса TransactionProviderHandler.
     *
     * @param repository Репозиторий для работы с банковскими счетами.
     */
    public TransactionProviderHandler(BankAccountRepositoryImpl repository) {
        this.repository = repository;
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
     * Обрабатывает транзакцию между банковскими счетами. Метод аннотирован @Transactional для обеспечения
     * атомарности операции. В случае ошибки ввода-вывода выбрасывается ForbiddenTransactionException.
     *
     * @param context Контекст транзакции, содержащий информацию о счетах и сумме транзакции.
     */
    @Transactional(rollbackFor = ForbiddenTransactionException.class)
    @Override
    public void handle(TransactionContext context) {
        try {
            Optional<List<BankAccount>> accountRecords = repository
                        .provideTransaction(List.of(context.getSenderBankAccount(), context.getReceiverBankAccount()));

            List<BankAccount> accountsAfterTransaction = accountRecords.orElseThrow();

            context.fillWithAccounts(accountsAfterTransaction);
        } catch (IOException e) {
            throw new ForbiddenTransactionException();
        }

        if (next != null) next.handle(context);
    }
}
