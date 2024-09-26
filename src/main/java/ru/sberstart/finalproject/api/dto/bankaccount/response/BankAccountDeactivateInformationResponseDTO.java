package ru.sberstart.finalproject.api.dto.bankaccount.response;

import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.BankAccountStates;

import java.util.UUID;

public record BankAccountDeactivateInformationResponseDTO (UUID bankId, UUID userId, String number, BankAccountStates state) {
}
