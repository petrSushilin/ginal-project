package ru.sberstart.finalproject.api.dto.bankaccount.response;

import ru.sberstart.finalproject.domain.enitity.bankaccount.enums.BankAccountStates;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BankAccountInformationResponseDTO (String bankName, String bankIdentityNumber, String userName, String userSurname, LocalDate birthdate, String phoneNumber, String passportNumber, LocalDate registryDate, String number, BigDecimal balance, BankAccountStates state) {
}
