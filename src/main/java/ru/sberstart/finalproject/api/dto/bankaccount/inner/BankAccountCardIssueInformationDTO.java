package ru.sberstart.finalproject.api.dto.bankaccount.inner;

import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.BankAccountStates;

import java.util.UUID;

public record BankAccountCardIssueInformationDTO (UUID id, UUID bankId, String userName, String userSurname, BankAccountStates state) {
}
