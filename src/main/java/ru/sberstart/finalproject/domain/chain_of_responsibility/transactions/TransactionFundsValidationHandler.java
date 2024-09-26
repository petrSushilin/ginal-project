package ru.sberstart.finalproject.domain.chain_of_responsibility.transactions;

import org.springframework.web.client.RestTemplate;
import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.TransactionTypes;
import ru.sberstart.finalproject.global.exceptions.ForbiddenTransactionException;

import java.math.BigDecimal;
import java.util.Optional;

public class TransactionFundsValidationHandler implements TransactionHandler {

    private TransactionHandler next;

    private final RestTemplate restTemplate;

    public TransactionFundsValidationHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void setNext(TransactionHandler next) {
        this.next = next;
    }

    @Override
    public void handle(TransactionContext context) {
        if (context.getTransactionTypes() == TransactionTypes.ADD_FUNDS) {
            Optional.ofNullable(context.getReceiverBankAccountNumber())
                    .filter(accountNumber -> validateFunds(accountNumber, context.getAmount()))
                    .orElseThrow(() -> new ForbiddenTransactionException("Подтверждение пополнения средств не удалось"));
        }

        if (next != null) {
            next.handle(context);
        }
    }

    private boolean validateFunds(String bankaccountNumber, BigDecimal amount) {
        // Пример вызова внешнего сервиса для валидации
        String validationUrl = String.format("http://localhost:8081/api/v1/bankaccounts/transaction-approve?bankaccountNumber==%s&amount=%s", bankaccountNumber, amount);
        return Boolean.TRUE.equals(restTemplate.getForObject(validationUrl, Boolean.class));
    }
}