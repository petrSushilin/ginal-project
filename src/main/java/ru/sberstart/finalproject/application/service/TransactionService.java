package ru.sberstart.finalproject.application.service;

import org.springframework.transaction.annotation.Transactional;
import ru.sberstart.finalproject.api.dto.bankaccount.request.BankAccountTransferTransactionRequestDTO;
import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;
import ru.sberstart.finalproject.domain.chain_of_responsibility.transactions.TransactionContext;
import ru.sberstart.finalproject.domain.chain_of_responsibility.transactions.TransactionHandler;
import ru.sberstart.finalproject.domain.chain_of_responsibility.transactions.TransactionHandlerFactory;

/**
 * Сервис для выполнения транзакций между банковскими счетами.
 * Использует цепочку обработчиков для валидации и выполнения транзакции.
 */
public class TransactionService {
    private TransactionHandlerFactory handlerFactory;

    public TransactionService(TransactionHandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    /**
     * Выполняет транзакцию на основе данных из DTO.
     * Создает контекст транзакции и передает его через цепочку обработчиков.
     *
     * @param transactionDTO DTO с информацией о транзакции.
     * @return Обновленный объект банковского счета отправителя после выполнения транзакции.
     */
    @Transactional
    public BankAccount executeTransaction(BankAccountTransferTransactionRequestDTO transactionDTO) {
        System.out.println(transactionDTO);
        TransactionContext context = new TransactionContext(transactionDTO);

        TransactionHandler handlerChain = handlerFactory.createTransactionHandler();

        handlerChain.handle(context);
        System.out.println("+");
        return context.getSenderBankAccount();
    }
}
