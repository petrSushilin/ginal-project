package ru.sberstart.finalproject.api.dto.bankaccount.inner;

import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.BankAccountStates;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BankAccountFullInformationDTO (UUID id, UUID bankId, String bankName, String bankIdentityNumber, UUID userId, String userName, String userSurname, LocalDate birthdate, String phoneNumber, String passportNumber, LocalDate registryDate, String number, BigDecimal balance, BankAccountStates state) {
}