package ru.sberstart.finalproject.bankaccount.api.dto;

import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;

import java.util.UUID;

public record BankAccountDeactivateInformationResponseDTO (UUID bankId, UUID userId, String number, BankAccountStates state) {
}
