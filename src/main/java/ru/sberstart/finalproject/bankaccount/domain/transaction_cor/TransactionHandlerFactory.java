package ru.sberstart.finalproject.bankaccount.domain.transaction_cor;

import org.springframework.stereotype.Component;

@Component
public class TransactionHandlerFactory {
    private final TransactionValidationInputHandler inputValidationTransactionHandler;
    private final TransactionValidationDataHandler dataValidationTransactionHandler;
    private final TransactionParamsCustomizerHandler preparingForTransactionHandler;
    private final TransactionProviderHandler operationTransactionHandler;
    private final TransactionSuccessValidatorHandler successValidateTransactionHandler;

    public TransactionHandlerFactory(TransactionValidationInputHandler inputValidationTransactionHandler, TransactionValidationDataHandler dataValidationTransactionHandler, TransactionParamsCustomizerHandler preparingForTransactionHandler, TransactionProviderHandler operationTransactionHandler, TransactionSuccessValidatorHandler successValidateTransactionHandler) {
        this.inputValidationTransactionHandler = inputValidationTransactionHandler;
        this.dataValidationTransactionHandler = dataValidationTransactionHandler;
        this.preparingForTransactionHandler = preparingForTransactionHandler;
        this.operationTransactionHandler = operationTransactionHandler;
        this.successValidateTransactionHandler = successValidateTransactionHandler;
    }

    public TransactionHandler createTransactionHandler() {
        inputValidationTransactionHandler.setNext(dataValidationTransactionHandler);
        dataValidationTransactionHandler.setNext(preparingForTransactionHandler);
        preparingForTransactionHandler.setNext(operationTransactionHandler);
        operationTransactionHandler.setNext(successValidateTransactionHandler);
        return inputValidationTransactionHandler;
    }
}
