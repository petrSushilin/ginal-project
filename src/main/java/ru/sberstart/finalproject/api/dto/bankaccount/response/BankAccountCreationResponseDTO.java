package ru.sberstart.finalproject.api.dto.bankaccount.response;

import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.BankAccountStates;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BankAccountCreationResponseDTO(UUID bankId, UUID userId, LocalDate registryDate, String number, BigDecimal balance, BankAccountStates state) {
}
