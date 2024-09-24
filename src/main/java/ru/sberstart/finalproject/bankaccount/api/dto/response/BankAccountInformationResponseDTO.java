package ru.sberstart.finalproject.bankaccount.api.dto.response;

import ru.sberstart.finalproject.bankaccount.domain.entity.enums.BankAccountStates;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BankAccountInformationResponseDTO (String bankName, String bankIdentityNumber, String userName, String userSurname, LocalDate birthdate, String phoneNumber, String passportNumber, LocalDate registryDate, String number, BigDecimal balance, BankAccountStates state) {
}
