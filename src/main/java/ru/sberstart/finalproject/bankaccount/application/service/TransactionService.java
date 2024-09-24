package ru.sberstart.finalproject.bankaccount.application.service;

import org.springframework.transaction.annotation.Transactional;
import ru.sberstart.finalproject.bankaccount.api.dto.request.BankAccountTransactionRequestDTO;
import ru.sberstart.finalproject.bankaccount.domain.entity.BankAccount;
import ru.sberstart.finalproject.bankaccount.domain.transaction_cor.TransactionContext;
import ru.sberstart.finalproject.bankaccount.domain.transaction_cor.TransactionHandler;
import ru.sberstart.finalproject.bankaccount.domain.transaction_cor.TransactionHandlerFactory;

/**
 * Сервис для выполнения транзакций между банковскими счетами.
 * Использует цепочку обработчиков для валидации и выполнения транзакции.
 */
public class TransactionService {
    private TransactionHandlerFactory handlerFactory;

    /**
     * Выполняет транзакцию на основе данных из DTO.
     * Создает контекст транзакции и передает его через цепочку обработчиков.
     *
     * @param transactionDTO DTO с информацией о транзакции.
     * @return Обновленный объект банковского счета отправителя после выполнения транзакции.
     */
    @Transactional
    public BankAccount executeTransaction(BankAccountTransactionRequestDTO transactionDTO) {
        TransactionContext context = new TransactionContext(transactionDTO);

        TransactionHandler handlerChain = handlerFactory.createTransactionHandler();

        handlerChain.handle(context);

        return context.getSenderBankAccount();
    }
}
