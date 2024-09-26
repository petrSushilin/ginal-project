package ru.sberstart.finalproject.infrastructure.configuration;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.sberstart.finalproject.api.controller.BankAccountController;
import ru.sberstart.finalproject.application.manager.BankAccountManager;
import ru.sberstart.finalproject.application.service.BankAccountService;
import ru.sberstart.finalproject.application.service.TransactionService;
import ru.sberstart.finalproject.domain.chain_of_responsibility.transactions.TransactionAddFundsHandler;
import ru.sberstart.finalproject.domain.chain_of_responsibility.transactions.TransactionFundsValidationHandler;
import ru.sberstart.finalproject.infrastructure.repostitory.implementation.BankAccountRepositoryImpl;
import ru.sberstart.finalproject.domain.chain_of_responsibility.transactions.TransactionHandlerFactory;
import ru.sberstart.finalproject.domain.chain_of_responsibility.transactions.TransactionParamsCustomizerHandler;
import ru.sberstart.finalproject.domain.chain_of_responsibility.transactions.TransactionProviderHandler;
import ru.sberstart.finalproject.domain.chain_of_responsibility.transactions.TransactionSuccessValidatorHandler;
import ru.sberstart.finalproject.domain.chain_of_responsibility.transactions.TransactionValidationDataHandler;
import ru.sberstart.finalproject.domain.chain_of_responsibility.transactions.TransactionValidationInputHandler;

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
    public TransactionService transactionService(TransactionHandlerFactory transactionHandlerFactory) {
        return new TransactionService(transactionHandlerFactory);
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
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public TransactionFundsValidationHandler transactionFundsValidationHandler(RestTemplate restTemplate) {
        return new TransactionFundsValidationHandler(restTemplate);
    }

    @Bean
    public TransactionParamsCustomizerHandler transactionParamsCustomizerHandler() {
        return new TransactionParamsCustomizerHandler();
    }

    @Bean
    public TransactionAddFundsHandler transactionAddFundsHandler(BankAccountRepositoryImpl bankAccountRepository) {
        return new TransactionAddFundsHandler(bankAccountRepository);
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
                                                               TransactionFundsValidationHandler transactionFundsValidationHandler,
                                                               TransactionParamsCustomizerHandler transactionParamsCustomizerHandler,
                                                               TransactionAddFundsHandler transactionAddFundsHandler,
                                                               TransactionProviderHandler transactionProviderHandler,
                                                               TransactionSuccessValidatorHandler transactionSuccessValidatorHandler) {
        return new TransactionHandlerFactory(transactionValidationInputHandler, transactionValidationDataHandler,
                transactionFundsValidationHandler, transactionParamsCustomizerHandler, transactionAddFundsHandler,
                transactionProviderHandler, transactionSuccessValidatorHandler);
    }
}
