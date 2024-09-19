package ru.sberstart.finalproject.bankaccount.api.dto;

import java.math.BigDecimal;

public record BankAccountTransactionDTO (String senderBankAccountNumber, String receiverBankAccountNumber, BigDecimal amount) {
}
