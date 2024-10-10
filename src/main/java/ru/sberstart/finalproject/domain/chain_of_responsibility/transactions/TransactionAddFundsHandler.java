package ru.sberstart.finalproject.domain.chain_of_responsibility.transactions;

import ru.sberstart.finalproject.api.dto.bankaccount.request.BankAccountTransferTransactionRequestDTO;
import ru.sberstart.finalproject.domain.enitity.bankaccount.BankAccount;
import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.TransactionTypes;
import ru.sberstart.finalproject.infrastructure.repostitory.BankAccountRepositoryImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionAddFundsHandler implements TransactionHandler {

    private TransactionHandler next;

    private final BankAccountRepositoryImpl bankAccountRepository;

    public TransactionAddFundsHandler(BankAccountRepositoryImpl bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public void setNext(TransactionHandler next) {
        this.next = next;
    }

    @Override
    public void handle(TransactionContext context) {
        if (context.getTransactionTypes() == TransactionTypes.ADD_FUNDS) {
            BankAccountTransferTransactionRequestDTO requestDTO = new BankAccountTransferTransactionRequestDTO(null, context.getReceiverBankAccountNumber(), context.getAmount());
            try {
                List<BankAccount> bankAccounts = new ArrayList<>();
                bankAccounts.add(context.getReceiverBankAccount());
                bankAccountRepository.provideTransaction(bankAccounts);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (next != null) {
            next.handle(context);
        }
    }
}
