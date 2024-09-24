package ru.sberstart.finalproject.bankaccount.api.dto.inner;

import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;

import java.util.UUID;

public record BankAccountCardIssueInformationDTO (UUID id, UUID bankId, String userName, String userSurname, BankAccountStates state) {
}
