package ru.sberstart.finalproject.api.dto.bankaccount.response;

import java.math.BigDecimal;

public record BankAccountSuccessTransactionResponseDTO(String senderBankAccountNumber, BigDecimal senderBankAccountBalance) {
}
