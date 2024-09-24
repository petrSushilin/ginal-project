package ru.sberstart.finalproject.bankaccount.api.dto.response;

import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BankAccountCreationResponseDTO(UUID bankId, UUID userId, LocalDate registryDate, String number, BigDecimal balance, BankAccountStates state) {
}
