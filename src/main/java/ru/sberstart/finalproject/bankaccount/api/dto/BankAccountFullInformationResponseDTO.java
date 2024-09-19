package ru.sberstart.finalproject.bankaccount.api.dto;

import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;

import java.math.BigDecimal;
import java.util.UUID;

public record BankAccountFullInformationResponseDTO (UUID bankId, UUID userId, String number, BigDecimal balance, BankAccountStates state) {
}
