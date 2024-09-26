package ru.sberstart.finalproject.api.dto.bankaccount.request;

import java.math.BigDecimal;

public record BankAccountTransferTransactionRequestDTO(String senderBankAccountNumber, String receiverBankAccountNumber, BigDecimal amount) {
}
