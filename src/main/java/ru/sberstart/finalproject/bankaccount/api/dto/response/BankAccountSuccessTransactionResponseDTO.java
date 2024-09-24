package ru.sberstart.finalproject.bankaccount.api.dto.response;

import java.math.BigDecimal;

public record BankAccountSuccessTransactionResponseDTO(String senderBankAccountNumber, BigDecimal senderBankAccountBalance) {
}
