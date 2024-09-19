package ru.sberstart.finalproject.bankaccount.infrastructure.configuration;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sberstart.finalproject.bankaccount.api.BankAccountController;
import ru.sberstart.finalproject.bankaccount.application.service.BankAccountService;
import ru.sberstart.finalproject.bankaccount.application.service.TransactionService;
import ru.sberstart.finalproject.bankaccount.domain.transaction_cor.TransactionValidationDataHandler;
import ru.sberstart.finalproject.bankaccount.infrastructure.repository.BankAccountRepositoryImpl;

@Configuration
public class BankAccountConfiguration {
    @Bean
    public BankAccountRepositoryImpl bankAccountRepository(DSLContext context) {
        return new BankAccountRepositoryImpl(context);
    }

    @Bean
    public BankAccountService bankAccountService(BankAccountRepositoryImpl bankAccountRepository, TransactionService transactionService) {
        return new BankAccountService(bankAccountRepository, transactionService);
    }

    @Bean
    public TransactionValidationDataHandler dataValidationHandler(BankAccountRepositoryImpl bankAccountRepository) {
        return new TransactionValidationDataHandler(bankAccountRepository);
    }

    @Bean
    public BankAccountController bankAccountController(BankAccountService bankAccountService) {
        return new BankAccountController(bankAccountService);
    }
}
