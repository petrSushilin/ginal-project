package ru.sberstart.finalproject.bankaccount.api.dto;

import java.math.BigDecimal;

public record BankAccountSuccessTransactionDTO (String senderBankAccountNumber, BigDecimal senderBankAccountBalance) {
}
