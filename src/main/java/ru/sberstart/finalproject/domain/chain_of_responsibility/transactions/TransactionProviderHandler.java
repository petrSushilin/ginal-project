package ru.sberstart.finalproject.domain.chain_of_responsibility.transactions;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;
import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.TransactionTypes;
import ru.sberstart.finalproject.infrastructure.repostitory.BankAccountRepositoryImpl;
import ru.sberstart.finalproject.global.exceptions.ForbiddenTransactionException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        if (context.getTransactionTypes() == TransactionTypes.TRANSFER) {
            processTransfer(context);
        } else if (next != null) {
            next.handle(context);
        }
    }

    public void processTransfer(TransactionContext context) {
        try {
            List<BankAccount> bankAccounts = new ArrayList<>();
            bankAccounts.add(context.getSenderBankAccount());
            bankAccounts.add(context.getReceiverBankAccount());
            List<BankAccount> accountsAfterTransaction = repository
                    .provideTransaction(bankAccounts)
                    .orElseThrow(ForbiddenTransactionException::new);
            context.fillWithAccounts(accountsAfterTransaction);
        } catch (IOException e) {
            throw new ForbiddenTransactionException();
        }
    }
}
