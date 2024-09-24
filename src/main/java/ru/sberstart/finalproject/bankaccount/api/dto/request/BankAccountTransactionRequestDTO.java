package ru.sberstart.finalproject.bankaccount.api.dto.request;

import java.math.BigDecimal;

public record BankAccountTransactionRequestDTO(String senderBankAccountNumber, String receiverBankAccountNumber, BigDecimal amount) {
}
