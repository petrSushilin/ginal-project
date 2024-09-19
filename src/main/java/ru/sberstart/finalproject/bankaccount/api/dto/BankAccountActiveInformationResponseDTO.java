package ru.sberstart.finalproject.bankaccount.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record BankAccountActiveInformationResponseDTO (UUID bankId, UUID userId, String number, BigDecimal balance) {
}
