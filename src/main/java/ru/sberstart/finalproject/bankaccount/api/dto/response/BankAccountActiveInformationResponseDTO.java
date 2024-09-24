package ru.sberstart.finalproject.bankaccount.api.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record BankAccountActiveInformationResponseDTO (UUID bankId, UUID userId, String number, BigDecimal balance) {
}
