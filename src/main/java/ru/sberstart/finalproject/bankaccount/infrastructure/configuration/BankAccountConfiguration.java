package ru.sberstart.finalproject.bankaccount.infrastructure.configuration;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sberstart.finalproject.bankaccount.api.BankAccountController;
import ru.sberstart.finalproject.bankaccount.application.manager.BankAccountManager;
import ru.sberstart.finalproject.bankaccount.application.service.BankAccountService;
import ru.sberstart.finalproject.bankaccount.application.service.TransactionService;
import ru.sberstart.finalproject.bankaccount.domain.transaction_cor.*;
import ru.sberstart.finalproject.bankaccount.infrastructure.repository.BankAccountRepositoryImpl;

@Configuration
public class BankAccountConfiguration {
    @Bean
    public BankAccountRepositoryImpl bankAccountRepository(DSLContext context) {
        return new BankAccountRepositoryImpl(context);
    }

    @Bean
    public BankAccountManager bankAccountManager() {
        return new BankAccountManager();
    }

    @Bean
    public TransactionService transactionService() {
        return new TransactionService();
    }

    @Bean
    public BankAccountService bankAccountService(BankAccountRepositoryImpl bankAccountRepository, TransactionService transactionService, BankAccountManager manager) {
        return new BankAccountService(bankAccountRepository, transactionService, manager);
    }

    @Bean
    public BankAccountController bankAccountController(BankAccountService bankAccountService) {
        return new BankAccountController(bankAccountService);
    }

    @Bean
    public TransactionValidationInputHandler transactionValidationInputHandler(BankAccountManager bankAccountManager) {
        return new TransactionValidationInputHandler(bankAccountManager);
    }

    @Bean
    public TransactionValidationDataHandler transactionValidationDataHandler(BankAccountRepositoryImpl bankAccountRepository, BankAccountManager manager) {
        return new TransactionValidationDataHandler(bankAccountRepository, manager);
    }

    @Bean
    public TransactionParamsCustomizerHandler transactionParamsCustomizerHandler(){
        return new TransactionParamsCustomizerHandler();
    }

    @Bean
    public TransactionProviderHandler transactionProviderHandler(BankAccountRepositoryImpl bankAccountRepository) {
        return new TransactionProviderHandler(bankAccountRepository);
    }

    @Bean
    public TransactionSuccessValidatorHandler transactionSuccessValidatorHandler () {
        return new TransactionSuccessValidatorHandler();
    }

    @Bean
    public TransactionHandlerFactory transactionHandlerFactory(TransactionValidationInputHandler transactionValidationInputHandler,
                                                               TransactionValidationDataHandler transactionValidationDataHandler,
                                                               TransactionParamsCustomizerHandler transactionParamsCustomizerHandler,
                                                               TransactionProviderHandler transactionProviderHandler,
                                                               TransactionSuccessValidatorHandler transactionSuccessValidatorHandler) {
        return new TransactionHandlerFactory(transactionValidationInputHandler, transactionValidationDataHandler,
                transactionParamsCustomizerHandler, transactionProviderHandler, transactionSuccessValidatorHandler);
    }
}
